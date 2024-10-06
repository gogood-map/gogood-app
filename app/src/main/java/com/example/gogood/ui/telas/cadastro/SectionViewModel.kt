package com.example.gogood.ui.telas.cadastro

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SectionViewModel : ViewModel() {
    private val _boxHeight = MutableStateFlow(400.dp)
    val boxHeight: StateFlow<Dp> = _boxHeight
    val currentSection = MutableStateFlow("CadastroSection")

    init {
        viewModelScope.launch {
            currentSection.collect { section ->
                _boxHeight.value = when (section) {
                    "CadastroSection" -> 620.dp
                    "DadosPessoaisSection" -> 600.dp
                    "ConcluidoSection" -> 550.dp
                    else -> 400.dp
                }
            }
        }
    }
}