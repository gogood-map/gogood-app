package com.example.gogood.ui.telas.cadastro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gogood.cadastro.DatePickerInput
import com.example.gogood.ui.theme.GoGoodTheme
import com.example.gogood.ui.theme.GogoodGreen

@Composable
fun DadosPessoaisSection(modifier: Modifier = Modifier, viewModel: SectionViewModel) {
    val genderState = remember { mutableStateOf("") }
    val birthDateState = remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TitleText("Dados Pessoais")
        Spacer(modifier = Modifier.height(14.dp))
        GenderField("Selecione sua identidade de gênero:", genderState)
        Spacer(modifier = Modifier.height(14.dp))
        BirthDateField("Data de nascimento*", birthDateState)
        Spacer(modifier = Modifier.height(8.dp))
        NavigationButtons(viewModel, "ConcluidoSection", "CadastroSection")
        Spacer(modifier = Modifier.height(18.dp))
        SocialLogin()
        Spacer(modifier = Modifier.height(10.dp))
        LoginLink()
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
        DadosPessoaisSection(modifier = modifier, viewModel = SectionViewModel())
    }
}