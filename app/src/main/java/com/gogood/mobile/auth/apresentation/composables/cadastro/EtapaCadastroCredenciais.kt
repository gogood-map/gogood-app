package com.gogood.mobile.auth.apresentation.composables.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.auth.apresentation.composables.GoogleIcon
import com.gogood.mobile.auth.apresentation.viewmodels.CadastroViewModel
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EtapaCadastroCredenciais() {
    val cadastroViewModel: CadastroViewModel = koinViewModel()
    val nomeState = remember {
        mutableStateOf(cadastroViewModel.usuarioCadastro.nome)
    }
    val emailState = remember { mutableStateOf(cadastroViewModel.usuarioCadastro.email) }
    val senhaState = remember { mutableStateOf(cadastroViewModel.usuarioCadastro.senha) }
    val confirmarSenhaState = remember { mutableStateOf(cadastroViewModel.usuarioCadastro.senha) }
    val exibirSenhaState = remember {
        mutableStateOf(false)
    }
    var habilitarProximo by remember {
        mutableStateOf(false)
    }

    val emailValido: (String) -> Boolean = {email->
        email.contains('@') && email.contains('.')
    }

    val senhaValida: (String) -> Boolean = {senha->
       senha.length >= 6 && senha.isNotBlank()
    }

    val nomeValido: (String) -> Boolean = {nome->
        nome.length >= 4 && nome.isNotBlank()
    }

    if(emailValido(emailState.value) && senhaValida(senhaState.value) && nomeValido(nomeState.value)){
        habilitarProximo = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText("Cadastre-se")
        Spacer(modifier = Modifier.height(14.dp))
        Campo("Nome", nomeState, nomeValido)
        Spacer(modifier = Modifier.height(14.dp))
        Campo("Email", emailState, emailValido)
        Spacer(modifier = Modifier.height(14.dp))
        CampoSenha("Senha",  senhaState, exibirSenhaState, senhaValida)
        Spacer(modifier = Modifier.height(14.dp))

        CampoSenha("Confirmar senha", confirmarSenhaState, exibirSenhaState, senhaValida)

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
        else{
            cadastroViewModel.usuarioCadastro.nome = nomeState.value
            cadastroViewModel.usuarioCadastro.email = emailState.value
            cadastroViewModel.usuarioCadastro.senha = senhaState.value
            NavigationButtons(nextSection = "DadosPessoaisSection", enableNext = habilitarProximo)
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

