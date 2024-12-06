package com.gogood.mobile.home.domain.usecases

import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.RotaHistoricoRequest
import com.gogood.mobile.home.domain.models.RotaHistoricoResponse
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SalvarRotaHistoricoUseCase(private val mapRepository: IMapRepository): ISalvarRotaHistoricoUseCase {
    override suspend fun invoke(rotaNova: RotaHistoricoRequest): Result<RotaHistoricoResponse> {
        try {
            val resultado = mapRepository.salvarRotaHistorico(rotaNova)
            if(resultado.isSuccessful){
                return Result.success(resultado.body()!!)
            }else{
                return Result.failure(Exception("Não foi possível conectar-se a API."))
            }
        }catch (e: ConnectException) {
        return Result.failure(Exception("Não foi possível conectar-se a API."))
        }catch (t: SocketTimeoutException){
            return Result.failure(Exception("Tempo de consulta excedido."))
        }
        catch (h: UnknownHostException){
            return Result.failure(Exception("API fora do ar."))
        }
    }
}