package com.gogood.mobile.auth.apresentation.composables.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.auth.apresentation.composables.GoogleIcon
import com.gogood.mobile.auth.apresentation.viewmodels.CadastroViewModel
import com.gogood.mobile.auth.apresentation.viewmodels.SectionViewModel
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CadastroSection(viewModelSection: SectionViewModel = koinViewModel(),
                    cadastroViewModel: CadastroViewModel = koinViewModel()
) {

    val nomeState = remember {
        mutableStateOf(cadastroViewModel.usuarioCadastro.nome)
    }
    val emailState = remember { mutableStateOf(cadastroViewModel.usuarioCadastro.email) }
    val senhaState = remember { mutableStateOf(cadastroViewModel.usuarioCadastro.senha) }
    val confirmarSenhaState = remember { mutableStateOf(cadastroViewModel.usuarioCadastro.senha) }
    val exibirSenhaState = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText("Cadastre-se")
        Spacer(modifier = Modifier.height(14.dp))
        InputField("Nome *", nomeState)
        Spacer(modifier = Modifier.height(14.dp))
        InputField("Email *", emailState)
        Spacer(modifier = Modifier.height(14.dp))
        PasswordInputField("Senha *",  senhaState, exibirSenhaState)
        Spacer(modifier = Modifier.height(14.dp))

        PasswordInputField("Confirmar senha *", confirmarSenhaState, exibirSenhaState)
        Column (modifier = Modifier.width(250.dp)){
            Text(text = "Exibir Senha",
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                modifier =
                Modifier
                    .align(Alignment.End)
                    .clickable {
                        exibirSenhaState.value = !exibirSenhaState.value
                    }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if(senhaState.value != confirmarSenhaState.value){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Text(text = "Senhas não coincindem.", color = Color.Red)
            }

        }
        else if(!emailState.value.contains('@') || !emailState.value.contains('.')){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Text(text = "Email inválido.", color = Color.Red)
            }
        }
        else{
            cadastroViewModel.usuarioCadastro.nome = nomeState.value
            cadastroViewModel.usuarioCadastro.email = emailState.value
            cadastroViewModel.usuarioCadastro.senha = senhaState.value
            NavigationButtons(viewModelSection, nextSection = "DadosPessoaisSection"){

            }
        }
       
        Spacer(modifier = Modifier.height(18.dp))
        SocialLogin()
        Spacer(modifier = Modifier.height(10.dp))
        LoginLink()
    }
}



@Composable
fun TitleText(text: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 30.sp,
            fontWeight = FontWeight.Black,
        )
    }
}

@Composable
fun InputField(label: String, state: MutableState<String>) {

    Column {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        BasicTextField(
            value = state.value,
            onValueChange = {  state.value = it },
            modifier = Modifier
                .width(250.dp)
                .height(45.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(start = 15.dp, top = 15.dp),
            singleLine = true,
        )
    }
}
@Composable
fun PasswordInputField(label: String, state: MutableState<String>, exibirSenha: MutableState<Boolean>) {

    Column {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        BasicTextField(

            value = state.value,
            onValueChange = {  state.value = it },
            visualTransformation = if (!exibirSenha.value) PasswordVisualTransformation() else VisualTransformation.None,

            modifier = Modifier
                .width(250.dp)
                .height(45.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(start = 15.dp, top = 15.dp),
            singleLine = true,

            )

    }
}


@Composable
fun NavigationButtons(sectionViewModel: SectionViewModel = koinViewModel(), nextSection: String, lastSection: String? = null,
                      click: ()-> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        if (lastSection != null) {
            IconButton(
                onClick = {
                    sectionViewModel.currentSection.value = lastSection
                },
                modifier = Modifier
                    .size(50.dp)
                    .shadow(8.dp, CircleShape)
                    .background(GogoodGray, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
        IconButton(
            onClick = {
                sectionViewModel.currentSection.value = nextSection
                click()
            },
            modifier = Modifier
                .size(50.dp)
                .shadow(8.dp, CircleShape)
                .background(GogoodGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next",
                tint = Color.White
            )
        }
    }
}

@Composable
fun SocialLogin() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Ou faça parte com",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            )
            Spacer(modifier = Modifier.height(10.dp))
            GoogleIcon()
        }
    }
}

@Composable
fun LoginLink() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            buildAnnotatedString {
                append("Já tem cadastro? Retome sua \n")
                append("segurança! ")
                withStyle(
                    style = SpanStyle(
                        color = GogoodGreen,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Login")
                }
            },
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
        )
    }
}

