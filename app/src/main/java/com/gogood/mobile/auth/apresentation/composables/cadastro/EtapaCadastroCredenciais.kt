package com.gogood.mobile.auth.apresentation.composables.cadastro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gogood.mobile.auth.apresentation.composables.Campo
import com.gogood.mobile.auth.apresentation.composables.CampoConfirmarSenha
import com.gogood.mobile.auth.apresentation.composables.CampoSenha
import com.gogood.mobile.auth.apresentation.composables.GoogleIcon
import com.gogood.mobile.auth.apresentation.viewmodels.CadastroViewModel
import com.gogood.mobile.ui.theme.GogoodGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EtapaCadastroCredenciais(navController: NavController) {
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
        cadastroViewModel.usuarioCadastro.nome = nomeState.value
        cadastroViewModel.usuarioCadastro.email = emailState.value
        cadastroViewModel.usuarioCadastro.senha = senhaState.value
        habilitarProximo = true

    }else{
        habilitarProximo = false
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText("Cadastre-se")
        Spacer(modifier = Modifier.height(14.dp))

        Campo("Nome", nomeState, "Seu nome",nomeValido)
        Spacer(modifier = Modifier.height(14.dp))

        Campo("Email", emailState, "seu@email.com",emailValido)
        Spacer(modifier = Modifier.height(14.dp))

        CampoSenha("Senha",  senhaState, exibirSenhaState, senhaValida)
        Spacer(modifier = Modifier.height(14.dp))

        CampoConfirmarSenha(label = "Confirmar senha",
            stateConfirmar = confirmarSenhaState,
            exibirSenha =  exibirSenhaState,
            stateSenha = senhaState,
            regraValida =  senhaValida)
        Spacer(modifier = Modifier.height(8.dp))


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
        NavigationButtons(nextSection = "DadosPessoaisSection", enableNext = habilitarProximo)

        Spacer(modifier = Modifier.height(18.dp))
        LoginLink(navController)
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
fun LoginLink(navController: NavController) {
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
            modifier = Modifier.clickable {
                navController.navigate("Login")
            },
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
        )
    }
}

