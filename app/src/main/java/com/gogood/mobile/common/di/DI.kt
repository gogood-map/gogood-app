package com.gogood.mobile.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.gogood.mobile.auth.apresentation.viewmodels.CadastroViewModel
import com.gogood.mobile.auth.apresentation.viewmodels.LoginViewModel
import com.gogood.mobile.auth.data.repository.IUserRepository
import com.gogood.mobile.auth.data.repository.remote.UserRepository
import com.gogood.mobile.auth.domain.services.UserService
import com.gogood.mobile.common.ApiClient
import com.gogood.mobile.home.domain.services.MapsService
import com.gogood.mobile.home.data.repository.remote.MapRepository
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.data.repository.local.MapRepositoryLocal
import com.gogood.mobile.home.domain.services.GooglePlacesService
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.menu.apresentation.viewmodels.MenuViewModel
import com.gogood.mobile.menu.data.repository.IEnderecoRepository
import com.gogood.mobile.menu.data.repository.remote.EnderecoRepository
import com.gogood.mobile.menu.domain.services.EnderecoService
import com.gogood.mobile.utils.ConexaoInternetObserver
import com.gogood.mobile.utils.LocalizacaoObserver
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DataStore<Preferences>> {
        provideDataStore(androidContext())
    }
    single<ConexaoInternetObserver>{
        ConexaoInternetObserver(androidContext())
    }
    single<LocalizacaoObserver>
    {
        LocalizacaoObserver(androidContext())
    }

    single<MapsService> {
        ApiClient.mapsService
    }
    single<UserService> {
        ApiClient.userService
    }
    single<EnderecoService> {
        ApiClient.enderecoService
    }
    single<GooglePlacesService> {
        ApiClient.googlePlacesService
    }


    single <IEnderecoRepository>{
        EnderecoRepository(get())
    }
    single<IMapRepository>{
        MapRepository(get(), get())
        //MapRepositoryLocal()
    }
    single<IUserRepository>{
        UserRepository(get(), get())
    }




    viewModel {
        MapaViewModel(get(), get(), get())
    }
    viewModel {
        MenuViewModel(get(), get())
    }
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        CadastroViewModel(get())
    }
}
val Context.dataStore by preferencesDataStore("user_preferences")
fun provideDataStore(context: Context): DataStore<Preferences> {
    return context.dataStore
}