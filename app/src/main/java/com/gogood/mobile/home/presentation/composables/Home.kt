package com.gogood.mobile.home.presentation.composables

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.gogood.mobile.common.apresentation.composables.Erro
import com.gogood.mobile.home.presentation.stateholders.MainStateHolder
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.utils.ILocalizacaoUtils
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("MissingPermission")
@Composable
fun Home(navController: NavController) {
    val contexto = LocalContext.current
    val localizacaoObserver = koinInject<ILocalizacaoUtils>()

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
                var tituloErro = "Houve um erro."
                var descricaoErro = "Tente novamente mais tarde."
                if(uiState is MainStateHolder.Error){
                    descricaoErro = uiState.message
                    tituloErro = uiState.titulo
                }
                Erro(titulo = tituloErro, descricao = descricaoErro) {
                    mapaViewModel.uiState.value = MainStateHolder.Content()
                }
            }

        }
    }








