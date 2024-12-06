package com.gogood.mobile.auth.apresentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.common.ApiClient.userService
import com.gogood.mobile.auth.domain.services.LoginRequest
import com.gogood.mobile.auth.data.repository.IUserRepository
import com.gogood.mobile.auth.domain.models.UsuarioLoginRequest
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    var isLoggedIn = mutableStateOf(false)
    var isError by mutableStateOf(false)
    var usuarioLogin by mutableStateOf(UsuarioLoginRequest())



    fun login(email: String, password: String) {
        viewModelScope.launch {
            val resposta = userService.login(LoginRequest(email, password))
            if (resposta.isSuccessful) {
                isLoggedIn.value = true
                userRepository.salvarUsuario(resposta.body()!!)
            }else{
                isError = true
            }
        }
    }

    fun verificarLogin(){
        viewModelScope.launch {
            userRepository.obterUsuarioSalvo().collect{usuario->
                if(usuario != null){
                    isLoggedIn.value = true
                }else{
                    isLoggedIn.value = false
                }
            }

        }


    }


}