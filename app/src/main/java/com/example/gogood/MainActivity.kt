package com.example.gogood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.gogood.menu.MenuPreview
import com.example.gogood.navegacao.AppNavegacao
import com.example.gogood.ui.telas.cadastro.CadastroApp
import com.example.gogood.ui.theme.GoGoodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoGoodTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CadastroApp(
                        modifier = Modifier.padding(innerPadding),
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GoGoodTheme {
        CadastroApp(rememberNavController())
    }
}
