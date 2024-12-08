package com.gogood.mobile.home.domain.usecases

import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.RotaResponse
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class BuscaRotaUseCase(private val mapRepository: IMapRepository): IBuscaRotaUseCase {
    override suspend fun invoke(
        meioTransporte: String,
        origem: String,
        destino: String
    ): Result<List<RotaResponse>> {
        try {
            val resultado = mapRepository.buscarRota(meioTransporte=meioTransporte, origem=origem,
                destino=destino)
            if(resultado.isSuccessful){
                return Result.success(resultado.body()!!)
            }else{
                return Result.failure(Exception("Não foi possível conectar-se a API."))
            }
        }catch (e: ConnectException){
            return Result.failure(Exception("Não foi possível conectar-se a API."))
        }catch (t: SocketTimeoutException){
            return Result.failure(Exception("Tempo de consulta excedido."))
        }catch (h: UnknownHostException){
            return Result.failure(Exception("API fora do ar."))
        }
    }
}