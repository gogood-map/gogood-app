package com.gogood.mobile.auth.apresentation.composables.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.gogood.mobile.auth.apresentation.composables.cadastro.Campo
import com.gogood.mobile.auth.apresentation.composables.cadastro.SocialLogin
import com.gogood.mobile.auth.apresentation.composables.cadastro.TitleText
import com.gogood.mobile.auth.apresentation.viewmodels.LoginViewModel
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginSection(navController: NavController, ) {
    val loginViewModel: LoginViewModel = koinViewModel()
    val emailState = remember { mutableStateOf( loginViewModel.usuarioLogin.email) }
    val senhaState = remember { mutableStateOf( loginViewModel.usuarioLogin.senha) }
    val emailValido: (String) -> Boolean = {email->
        email.contains('@') && email.contains('.')
    }

    val senhaValida: (String) -> Boolean = {senha->
        senha.length >= 6 && senha.isNotBlank()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleText("Login")
        Spacer(modifier = Modifier.height(14.dp))
        Campo("Email", emailState, emailValido)
        Spacer(modifier = Modifier.height(14.dp))
        Campo("Senha", senhaState, senhaValida)
        Spacer(modifier = Modifier.height(8.dp))
        LoginButton(){
            loginViewModel.login(emailState.value, senhaState.value)
            if(loginViewModel.isLoggedIn){
                navController.navigate("Mapa")
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
        RegisterLink()
    }
}

@Composable
fun LoginButton(click: ()->Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {

        IconButton(
            onClick = {
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
fun RegisterLink() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            buildAnnotatedString {
                append("É novo na nossa plataforma? ")
                withStyle(
                    style = SpanStyle(
                        color = GogoodGreen,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Cadastrar")
                }
            },
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
        )
    }
}