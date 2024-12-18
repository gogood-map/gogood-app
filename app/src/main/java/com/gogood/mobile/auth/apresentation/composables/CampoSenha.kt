package com.gogood.mobile.auth.apresentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.ui.theme.GogoodOptionRed

@Composable
fun CampoSenha(label: String, state: MutableState<String>, exibirSenha: MutableState<Boolean>,
               regraValida: (String)->Boolean,
               validoParam: Boolean = true) {
    var valido by remember {
        mutableStateOf(validoParam)
    }
    var corInput by remember {
        mutableStateOf(Color.Black)
    }
    var textoErro by remember {
        mutableStateOf("")
    }
    Column {
        Text(
            color = corInput,
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        BasicTextField(

            value = state.value,
            onValueChange = {
                it.trim()
                state.value = it.trim()
                valido = regraValida(state.value)
            },
            visualTransformation = if (!exibirSenha.value) PasswordVisualTransformation() else VisualTransformation.None,

            modifier = Modifier
                .width(250.dp)
                .height(45.dp)
                .border(1.dp, corInput, RoundedCornerShape(8.dp))
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(start = 15.dp, top = 15.dp),
            singleLine = true,

            )
        if(!valido){
            corInput = GogoodOptionRed
            if(state.value.isBlank()){
                textoErro = "Campo obrigatório"
            }else{
                textoErro = "Utilize 6 ou mais caracteres"
            }
            Text(text = textoErro, color = corInput, fontSize = 10.sp)
        }else{
            corInput = Color.Black
        }
    }
}


@Composable
fun CampoConfirmarSenha(label: String,
                        stateSenha: MutableState<String>,
                        stateConfirmar: MutableState<String>,
                        exibirSenha: MutableState<Boolean>,
                        regraValida: (String)->Boolean,
                        validoParam: Boolean = true) {
    var valido by remember {
        mutableStateOf(validoParam)
    }
    var corInput by remember {
        mutableStateOf(Color.Black)
    }
    var textoErro by remember {
        mutableStateOf("")
    }
    Column {
        Text(
            color = corInput,
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        BasicTextField(

            value = stateConfirmar.value,
            onValueChange = {
                it.trim()
                stateConfirmar.value = it.trim()
                valido = regraValida(stateConfirmar.value)
            },
            visualTransformation = if (!exibirSenha.value) PasswordVisualTransformation() else VisualTransformation.None,

            modifier = Modifier
                .width(250.dp)
                .height(45.dp)
                .border(1.dp, corInput, RoundedCornerShape(8.dp))
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(start = 15.dp, top = 15.dp),
            singleLine = true,

            )
        if(!valido || (stateSenha.value != stateConfirmar.value)){
            corInput = GogoodOptionRed
            if(!valido){
                if(stateConfirmar.value.isBlank()){
                    textoErro = "Campo obrigatório"
                }
                if(stateConfirmar.value.length < 6){
                    textoErro = "Utilize 6 ou mais caracteres"
                }
            }
            if(stateConfirmar.value.isNotEmpty() &&(stateSenha.value != stateConfirmar.value)){
                textoErro = "Senhas diferentes"
            }
            Text(text = textoErro, color = corInput, fontSize = 10.sp)
        }
        else{
            corInput = Color.Black
        }
    }
}