package com.gogood.mobile.auth.apresentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gogood.mobile.ui.theme.GoGoodTheme

@Composable
fun SolicitacaoLogin(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Faça ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            TextoNavegacao(
                navController = navController,
                navigateTo = "Login",
                text = "Login"
            )
            Text(
                text = " ou ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            TextoNavegacao(
                navController = navController,
                navigateTo = "Cadastro",
                text = "Cadastre-se"
            )
        }
        Text(
            modifier = Modifier
                .width(200.dp),
            text = "Entre com uma conta para acessar nosso serviço.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TextoNavegacao(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateTo: String,
    text: String
) {
    TextButton(
        modifier = modifier,
        onClick = { navController.navigate(navigateTo) },
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SolicitacaoLoginPreview(modifier: Modifier = Modifier) {
    GoGoodTheme {
        SolicitacaoLogin(modifier = modifier, navController = rememberNavController())
    }
}