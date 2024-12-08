package com.gogood.mobile.menu.apresentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gogood.mobile.auth.data.repository.IUserRepository
import com.gogood.mobile.auth.domain.models.UsuarioResponse
import com.gogood.mobile.home.domain.models.RotaHistoricoResponse
import com.gogood.mobile.menu.data.repository.IEnderecoRepository
import com.gogood.mobile.menu.domain.models.EnderecoResponse
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class MenuViewModel(private val userRepository: IUserRepository, private val enderecoRepository: IEnderecoRepository) : ViewModel() {
    var abaMenu by mutableStateOf(0)
    var closeMenu = mutableStateOf({})

    var usuario by mutableStateOf<UsuarioResponse?>(null)
    var enderecosFavoritos = mutableStateListOf<EnderecoResponse>()
    var historicoRotas = mutableStateListOf<RotaHistoricoResponse>()
    init{
        viewModelScope.launch {
            userRepository.obterUsuarioSalvoLocal().collect {
                usuario = it
                obterEnderecosFavoritos()
                obterHistoricoRotas()
            }
        }
    }

     fun obterEnderecosFavoritos(){
         viewModelScope.launch {
             usuario?.let {dadosUsuario->
                 var resposta =  enderecoRepository.obterEnderecosFavoritos(dadosUsuario.userId!!)
                 if(resposta.isSuccessful){
                     if(resposta.body() != null){
                         enderecosFavoritos.clear()
                         enderecosFavoritos.addAll(resposta.body()!!)
                     }
                 }
             }
         }
    }
    fun obterHistoricoRotas(){
        viewModelScope.launch {
            usuario?.let {dadosUsuario->
                var resposta =  enderecoRepository.obterHistoricoRota(dadosUsuario.userId!!)
                if(resposta.isSuccessful){
                    if(resposta.body() != null){
                        historicoRotas.clear()
                        historicoRotas.addAll(resposta.body()!!)
                    }
                }
            }
        }
    }

}