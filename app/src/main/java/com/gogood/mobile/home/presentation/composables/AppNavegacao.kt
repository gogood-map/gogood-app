package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gogood.mobile.auth.apresentation.composables.cadastro.CadastroTela
import com.gogood.mobile.auth.apresentation.composables.login.LoginTela
import com.gogood.mobile.ui.theme.GoGoodTheme

@Composable
fun AppNavegacao() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController,
            startDestination = "Mapa",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("Mapa") {
                Mapa(navController)
            }
            composable("Login") {
                LoginTela(navController)
            }
            composable("Cadastro") {
                CadastroTela(navController)
            }
        }
    }
}


class NavegacaoViewModel(): ViewModel(){

}

@Preview
@Composable
fun TelaNavegacaoPreview(modifier: Modifier = Modifier) {
    GoGoodTheme {
        AppNavegacao()
    }
}