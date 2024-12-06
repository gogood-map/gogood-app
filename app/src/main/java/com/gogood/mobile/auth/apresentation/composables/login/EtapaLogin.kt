package com.gogood.mobile.auth.apresentation.composables.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import com.gogood.mobile.auth.apresentation.composables.Campo
import com.gogood.mobile.auth.apresentation.composables.CampoSenha
import com.gogood.mobile.auth.apresentation.composables.cadastro.SocialLogin
import com.gogood.mobile.auth.apresentation.composables.cadastro.TitleText
import com.gogood.mobile.auth.apresentation.viewmodels.LoginViewModel
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginSection(navController: NavController) {
    val loginViewModel: LoginViewModel = koinViewModel()
    val emailState = remember { mutableStateOf( loginViewModel.usuarioLogin.email) }
    val senhaState = remember { mutableStateOf( loginViewModel.usuarioLogin.senha) }
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

    habilitarProximo = emailValido(emailState.value) && senhaValida(senhaState.value)

    if(loginViewModel.isLoggedIn.value){
        navController.navigate("Mapa")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleText("Login")
        Spacer(modifier = Modifier.height(14.dp))
        Campo("Email", emailState, "seu@email.com",emailValido)
        Spacer(modifier = Modifier.height(14.dp))
        CampoSenha(label = "Senha", state= senhaState, exibirSenha = exibirSenhaState, senhaValida)
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
        Spacer(modifier = Modifier.height(8.dp))
        LoginButton(habilitarProximo){
            if(habilitarProximo){
                loginViewModel.login(emailState.value, senhaState.value)

            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        if(loginViewModel.isError){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Text(text = "Email ou senha inválido. Tente novamente.", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
        SocialLogin()
        Spacer(modifier = Modifier.height(10.dp))
        RegisterLink(navController)
    }
}

