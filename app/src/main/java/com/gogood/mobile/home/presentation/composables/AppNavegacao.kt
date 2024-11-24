package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gogood.mobile.auth.apresentation.composables.cadastro.CadastroTela
import com.gogood.mobile.auth.apresentation.composables.login.LoginTela
import com.gogood.mobile.home.presentation.stateholders.MainStateHolder
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GoGoodTheme
import com.gogood.mobile.utils.ConexaoInternetObserver
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavegacao() {


    val navController = rememberNavController()
    val mapaViewModel = koinViewModel<MapaViewModel>()




    Scaffold { innerPadding ->
        NavHost(
            navController,
            startDestination = "Mapa",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("Mapa") {
                Home(navController)
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




@Preview
@Composable
fun TelaNavegacaoPreview(modifier: Modifier = Modifier) {
    GoGoodTheme {
        AppNavegacao()
    }
}