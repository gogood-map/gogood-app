package com.gogood.mobile.auth.apresentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.auth.data.repository.IUserRepository
import com.gogood.mobile.auth.domain.models.UsuarioCadastroRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class CadastroViewModel(private val userRepository: IUserRepository) : ViewModel() {
    private val _boxHeight = MutableStateFlow(480.dp)
    val boxHeight: StateFlow<Dp> = _boxHeight
    val currentSection = MutableStateFlow("CadastroSection")
    var usuarioCadastro by mutableStateOf(UsuarioCadastroRequest())
    var isError by mutableStateOf(false)
    var isOk by mutableStateOf(false)
    var isLoading by mutableStateOf(true)


    init {
        viewModelScope.launch {
            currentSection.collect { section ->
                _boxHeight.value = when (section) {
                    "CadastroSection" -> 640.dp
                    "DadosPessoaisSection" -> 600.dp
                    "ConcluidoSection" -> 550.dp
                    else -> 400.dp
                }
            }
        }
    }

    fun cadastrar(){
        viewModelScope.launch {

            usuarioCadastro.dt_Nascimento =  LocalDate.parse(usuarioCadastro.dt_Nascimento, DateTimeFormatter
                .ofPattern("dd/MM/yyyy")).toString()
            usuarioCadastro.genero = usuarioCadastro.genero.lowercase(Locale.getDefault())

            if(usuarioCadastro.genero == "Prefiro não dizer"){
                usuarioCadastro.genero = "não especificado"
            }

            var resposta = userRepository.register(usuarioCadastro)

            if(resposta.isSuccessful){
                isLoading = false
                isOk = true

                userRepository.salvarUsuarioLocal(resposta.body()!!)


            }else{
                isError = true
                isLoading = false
            }

        }
    }

}