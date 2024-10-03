package com.example.gogood.ui.telas.cadastro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DadosPessoaisSection(modifier: Modifier = Modifier, viewModel: SectionViewModel) {
    val genderState = remember { mutableStateOf("") }
    val birthDateState = remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TitleText("Dados Pessoais")
        Spacer(modifier = Modifier.height(14.dp))
        GenderField("Gênero*", genderState)
        Spacer(modifier = Modifier.height(14.dp))
        BirthDateField("Data de nascimento*", birthDateState)
        Spacer(modifier = Modifier.height(8.dp))
        NavigationButtons(viewModel, "PersonalizacaoSection", "CadastroSection")
    }
}

@Composable
fun GenderField(modifier: Modifier = Modifier) {
    
}