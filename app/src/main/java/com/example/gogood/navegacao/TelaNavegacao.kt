package com.example.gogood.navegacao

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gogood.mapa.HeatmapMap
import com.example.gogood.menu.Menu
import com.example.gogood.ui.theme.GoGoodTheme
import kotlinx.serialization.Serializable

@Serializable
object MapaDeCalor

@Composable
fun TelaNavegacao() {
    val navController = rememberNavController()

    ModalNavigationDrawer( drawerContent = {
        ModalDrawerSheet(

        ) { Menu() }
    }) {
        Scaffold(
            topBar = { },
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                startDestination = MapaDeCalor
            ) {
                composable<MapaDeCalor> { HeatmapMap(navController) }
            }
        }
    }
}

@Preview
@Composable
fun TelaNavegacaoPreview(modifier: Modifier = Modifier) {
    GoGoodTheme {
        TelaNavegacao()
    }
}