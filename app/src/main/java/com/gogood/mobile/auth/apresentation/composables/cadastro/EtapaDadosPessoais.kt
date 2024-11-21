package com.gogood.mobile.auth.apresentation.composables.cadastro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.auth.apresentation.viewmodels.CadastroViewModel
import com.gogood.mobile.ui.theme.GoGoodTheme
import com.gogood.mobile.ui.theme.GogoodGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EtapaDadosPessoais(modifier: Modifier = Modifier) {
    val cadastroViewModel: CadastroViewModel = koinViewModel()
    val genderState = remember { mutableStateOf(cadastroViewModel.usuarioCadastro.genero) }
    val birthDateState = remember { mutableStateOf(cadastroViewModel.usuarioCadastro.dt_Nascimento) }
    var habilitarProximo by remember{
        mutableStateOf(false)
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TitleText("Dados Pessoais")
        Spacer(modifier = Modifier.height(14.dp))
        GenderField("Selecione sua identidade de gênero:", genderState)
        Spacer(modifier = Modifier.height(14.dp))
        if(genderState.value.isEmpty()){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Text(text = "Escolha ao menos uma das opções.", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
        BirthDateField("Data de nascimento*", birthDateState)
        Spacer(modifier = Modifier.height(8.dp))

        if(birthDateState.value.isEmpty()){

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Text(text = "Data de Nascimento não informada.", color = Color.Red)
            }
        }

        if(birthDateState.value.isNotEmpty() && genderState.value.isNotEmpty()){
            cadastroViewModel.usuarioCadastro.genero = genderState.value
            cadastroViewModel.usuarioCadastro.dt_Nascimento = birthDateState.value
            habilitarProximo = true
        }else{
            habilitarProximo = false
        }

        NavigationButtons("ConcluidoSection", "CadastroSection",habilitarProximo
        ) {
            cadastroViewModel.cadastrar()
        }
    }
}

@Composable
fun GenderField(
    label: String,
    genderState: MutableState<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            GenderRadioButton("Masculino", genderState)
            GenderRadioButton("Feminino", genderState)
            GenderRadioButton("Prefiro não dizer", genderState)
            GenderRadioButton("Outro", genderState)
        }
    }
}

@Composable
fun GenderRadioButton(
    text: String,
    genderState: MutableState<String>,
) {
    Row(
        modifier = Modifier.height(32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RadioButton(
            selected = genderState.value == text,
            onClick = { genderState.value = text },
            colors = RadioButtonDefaults.colors(
                selectedColor = GogoodGreen,
                unselectedColor = Color(0xFFC4D1E2)
            )
        )
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun BirthDateField(
    label: String,
    birthDateState: MutableState<String>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        DatePickerInput(
            label = "dd/mm/yyyy",
            selectedDate = birthDateState.value,
            onDateSelected = { birthDateState.value = it },
            modifier = Modifier
                .height(45.dp)
                .width(250.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DadosPessoaisSectionPreview(modifier: Modifier = Modifier) {
    GoGoodTheme {
        EtapaDadosPessoais(modifier = modifier)
    }
}