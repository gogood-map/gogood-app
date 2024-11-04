package com.gogood.mobile.home.presentation.composables

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gogood.mobile.auth.apresentation.composables.cadastro.CadastroTela
import com.gogood.mobile.auth.apresentation.composables.login.LoginTela
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GoGoodTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import org.koin.compose.viewmodel.koinViewModel

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




@Preview
@Composable
fun TelaNavegacaoPreview(modifier: Modifier = Modifier) {
    GoGoodTheme {
        AppNavegacao()
    }
}