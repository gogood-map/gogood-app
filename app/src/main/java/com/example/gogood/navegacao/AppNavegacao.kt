package com.example.gogood.navegacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.gogood.login.LoginApp
import com.example.gogood.ui.telas.mapa.MapaDeCalor
import com.example.gogood.menu.Menu
import com.example.gogood.ui.telas.cadastro.CadastroTela
import com.example.gogood.ui.telas.login.LoginTela
import com.example.gogood.ui.theme.GoGoodTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoGoodTheme {
                AppNavegacao()
            }
        }
    }
}

@Serializable
object MapaDeCalor

@Serializable
object Login

@Serializable
object Cadastro

@Serializable
object Menu

@Composable
fun AppNavegacao() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController,
            startDestination = MapaDeCalor,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<MapaDeCalor> {
                MapaDeCalor(navController)
            }
            composable<Login> {
                LoginTela(navController)
            }
            composable<Cadastro> {
                CadastroTela(navController)
            }
            composable<Menu> {
                Menu(navController)
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