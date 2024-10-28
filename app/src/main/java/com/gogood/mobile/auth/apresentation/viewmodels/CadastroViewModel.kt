package com.gogood.mobile.auth.apresentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.auth.data.repository.IUserRepository
import com.gogood.mobile.auth.domain.models.UsuarioCadastroRequest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CadastroViewModel( private val userRepository: IUserRepository):ViewModel() {
    var usuarioCadastro by mutableStateOf(UsuarioCadastroRequest())
    var isError by mutableStateOf(false)
    var isOk by mutableStateOf(false)
    var isLoading by mutableStateOf(true)


    fun registrar(){
        viewModelScope.launch {

            usuarioCadastro.dt_Nascimento =  LocalDate.parse(usuarioCadastro.dt_Nascimento, DateTimeFormatter
                                                                .ofPattern("dd/MM/yyyy")).toString()
            usuarioCadastro.genero = usuarioCadastro.genero.toLowerCase()

            if(usuarioCadastro.genero == "Prefiro não dizer"){
                usuarioCadastro.genero = "não especificado"
            }

            var resposta = userRepository.register(usuarioCadastro)

            if(resposta.isSuccessful){
                isLoading = false
                isOk = true

                userRepository.salvarUsuario(resposta.body()!!)


            }else{
                isError = true
                isLoading = false
            }

        }

    }

}