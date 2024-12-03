package com.gogood.mobile.home.domain.usecases

import androidx.lifecycle.viewModelScope
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class BuscaEnderecoUseCase(private val mapRepository: IMapRepository): IBuscaEnderecoUseCase {

    override suspend operator fun invoke(entrada: String): Result<BuscaEnderecoResponse>{
        try {
            val requisicao = mapRepository.buscarEndereco(entrada)

            if(requisicao.isSuccessful &&
                requisicao.body() != null &&
                requisicao.body()!!.candidates.isNotEmpty())
            {
                return Result.success(requisicao.body()!!)
            }
            else{
                return Result.failure(Exception("Não foram encontrados resultados para esse endereço."))
            }
        }
        catch (e: ConnectException){
            return Result.failure(Exception("Não foi possível conectar-se a API."))
        }catch (t: SocketTimeoutException){
            return Result.failure(Exception("Tempo de consulta excedido."))
        }catch (h: UnknownHostException){
            return Result.failure(Exception("API fora do ar."))
        }
    }
}