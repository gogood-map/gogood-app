package com.gogood.mobile.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.gogood.mobile.address.data.repository.IAddressRepository
import com.gogood.mobile.address.data.repository.remote.AddressRepository
import com.gogood.mobile.address.domain.services.AddressService
import com.gogood.mobile.address.presentation.viewModel.AddressViewModel
import com.gogood.mobile.auth.apresentation.viewmodels.CadastroViewModel
import com.gogood.mobile.auth.apresentation.viewmodels.LoginViewModel
import com.gogood.mobile.auth.data.repository.IUserRepository
import com.gogood.mobile.auth.data.repository.remote.UserRepository
import com.gogood.mobile.auth.domain.services.UserService
import com.gogood.mobile.common.ApiClient
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.data.repository.local.MapRepositoryLocal
import com.gogood.mobile.home.data.repository.remote.MapRepository
import com.gogood.mobile.home.domain.services.GooglePlacesService
import com.gogood.mobile.home.domain.services.MapsService
import com.gogood.mobile.home.domain.usecases.IObterCoordenadasOcorrenciaRaioUseCase
import com.gogood.mobile.home.domain.usecases.IObterRelatorioRaioUseCase
import com.gogood.mobile.home.domain.usecases.ObterCoordenadasOcorrenciasRaioUseCase
import com.gogood.mobile.home.domain.usecases.ObterRelatorioRaioUseCase
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.menu.apresentation.viewmodels.MenuViewModel
import com.gogood.mobile.menu.data.repository.IEnderecoRepository
import com.gogood.mobile.menu.data.repository.remote.EnderecoRepository
import com.gogood.mobile.menu.domain.services.EnderecoService
import com.gogood.mobile.utils.ConexaoUtils
import com.gogood.mobile.utils.IConexaoUtils
import com.gogood.mobile.utils.ILocalizacaoUtils
import com.gogood.mobile.utils.LocalizacaoUtils
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DataStore<Preferences>> {
        provideDataStore(androidApplication())
    }

    single<ILocalizacaoUtils>
    {
        LocalizacaoUtils(androidApplication())
    }
    single<IConexaoUtils>{
        ConexaoUtils(androidApplication())
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

    single<AddressService> { ApiClient.addressService }


    single<IAddressRepository> { AddressRepository(get()) }


    single <IEnderecoRepository>{
        EnderecoRepository(get())
    }
    single<IMapRepository>{
        //MapRepository(get(), get(), get())
        MapRepositoryLocal(get())
    }
    single<IUserRepository>{
        UserRepository(get(), get())
    }

    single <IObterCoordenadasOcorrenciaRaioUseCase>{
        ObterCoordenadasOcorrenciasRaioUseCase(get())
    }
    single<IObterRelatorioRaioUseCase> {
        ObterRelatorioRaioUseCase(get())
    }


    viewModel {
        MapaViewModel(get(), get(), get(), get(), get())
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

    viewModel { AddressViewModel(get()) }

}
val Context.dataStore by preferencesDataStore("user_preferences")
fun provideDataStore(context: Context): DataStore<Preferences> {
    return context.dataStore
}