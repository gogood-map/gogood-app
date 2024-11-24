package com.gogood.mobile.home.presentation.composables

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gogood.mobile.home.presentation.stateholders.MainStateHolder
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.utils.ConexaoInternetObserver
import com.gogood.mobile.utils.LocalizacaoObserver
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("MissingPermission")
@Composable
fun Home(navController: NavController) {
    val localizacaoObserver = koinInject<LocalizacaoObserver>()

    val mapaViewModel: MapaViewModel = koinViewModel()

    val getPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            localizacaoObserver.permissaoLocalizacao.value = true
        }
    }
    SideEffect {
        getPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
    val conectado =mapaViewModel.conectado.collectAsState()

    if(conectado.value){
        mapaViewModel.uiState.value = MainStateHolder.Content()
    }else{
        mapaViewModel.uiState.value = (MainStateHolder.NoConnection)
    }

    val uiState = mapaViewModel.uiState.value




    when(uiState){
        is MainStateHolder.Loading->{
            Loading()
        }
        is MainStateHolder.NoConnection->{
            AvisoSemConexao()
        }
        is MainStateHolder.Content ->{
            Mapa(navController= navController)
        }

        is MainStateHolder.Error, null -> {

        }
    }

}




