package com.gogood.mobile.menu.apresentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.auth.data.repository.IUserRepository
import com.gogood.mobile.auth.domain.models.UsuarioResponse
import com.gogood.mobile.menu.data.repository.IEnderecoRepository
import com.gogood.mobile.menu.domain.models.EnderecoResponse
import kotlinx.coroutines.launch

class MenuViewModel(private val userRepository: IUserRepository, private val enderecoRepository: IEnderecoRepository) : ViewModel() {
    var usuario by mutableStateOf<UsuarioResponse?>(null)
    var enderecosFavoritos = mutableStateListOf<EnderecoResponse>()

    init{
        viewModelScope.launch {
            userRepository.obterUsuarioSalvo().collect {
                usuario = it


                usuario?.let {dadosUsuario->
                   var resposta =  enderecoRepository.obterEnderecosFavoritos(dadosUsuario.userId!!)
                    if(resposta.isSuccessful){
                        if(resposta.body() != null){
                            enderecosFavoritos.addAll(resposta.body()!!)
                        }
                    }
                }

            }

        }


    }

    fun buscarEnderecosFavoritos(){

    }


}