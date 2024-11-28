package com.gogood.mobile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.QtdMes
import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.gogood.mobile.home.domain.usecases.IObterCoordenadasOcorrenciaRaioUseCase
import com.gogood.mobile.home.domain.usecases.IObterRelatorioRaioUseCase
import com.gogood.mobile.home.presentation.stateholders.MainStateHolder
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.utils.IConexaoUtils
import com.gogood.mobile.utils.ILocalizacaoUtils
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val instantExecutorRole = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val coordenadaTeste = LatLng(-23.550395929666593, -46.63396345499372)
    private val zoomTeste = 16f
    @Mock
    private lateinit var observer: Observer<MainStateHolder>

    private lateinit var viewModel: MapaViewModel
    @Mock
    private lateinit var obterCoordenadasOcorrenciasRaioUseCase: IObterCoordenadasOcorrenciaRaioUseCase
    @Mock
    private lateinit var mapRepository: IMapRepository
    @Mock
    private lateinit var localizacaoUtils: ILocalizacaoUtils
    @Mock
    private lateinit var conexaoUtils: IConexaoUtils
    @Mock
    private lateinit var obterRelatorioRaioUseCase: IObterRelatorioRaioUseCase

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)

    }
    @After
    fun end(){
        viewModel._uiState.removeObserver(observer)
        Dispatchers.resetMain()
    }

    private fun setupViewModel(){
        viewModel = MapaViewModel(
            obterCoordenadasOcorrenciasRaioUseCase = obterCoordenadasOcorrenciasRaioUseCase,
            obterRelatorioRaioUseCase = obterRelatorioRaioUseCase,
            localizacaoUtils = localizacaoUtils,
            mapRepository = mapRepository,
            conexaoUtils = conexaoUtils,
        )
    }

    @Test
    fun `Inicializar app com internet estado de tela vira Content`()= runTest {

        whenever(conexaoUtils.observarConexao)
            .thenReturn(
                flowOf(true)
            )

        setupViewModel()
        advanceUntilIdle()

        Assert.assertTrue(viewModel._uiState.value is MainStateHolder.Content)
    }

    @Test
    fun `Inicializar app sem internet estado de tela vira NoConnection`()= runTest {
        whenever(conexaoUtils.observarConexao)
            .thenReturn(
                flowOf(false)
            )


        setupViewModel()
        advanceUntilIdle()

        Assert.assertTrue(viewModel._uiState.value is MainStateHolder.NoConnection)
    }
    @Test
    fun `Buscar Relatório e retornar ok, estado deve permanecer content`()= runTest {
        whenever(conexaoUtils.observarConexao)
            .thenReturn(
                flowOf(true)
            )

        whenever(obterRelatorioRaioUseCase(coordenadaTeste, 1.25))
            .thenReturn(Result.success(
                RelatorioOcorrenciasResponse(
                    qtdOcorrencias = 0,
                    qtdMes = QtdMes(),
                    top5Ocorrencias = emptyList()
                )
            ))

        setupViewModel()
        viewModel.buscarRelatorioRaio()
        advanceUntilIdle()

        Assert.assertTrue(viewModel._uiState.value is MainStateHolder.Content)
    }

    @Test
    fun `Buscar Relatório e retornar erro, estado deve virar error`()= runTest {
        whenever(conexaoUtils.observarConexao)
            .thenReturn(
                flowOf(true)
            )


        whenever(obterRelatorioRaioUseCase(coordenadaTeste, 1.25))
            .thenReturn(Result.failure(Exception("")))

        setupViewModel()
        viewModel.buscarRelatorioRaio()
        advanceUntilIdle()

        Assert.assertTrue(viewModel._uiState.value is MainStateHolder.Error)
    }

    @Test
    fun `Buscar Ocorrencias e retornar ok, estado deve permanecer content`()= runTest {
        whenever(conexaoUtils.observarConexao)
            .thenReturn(
                flowOf(true)
            )



        whenever(obterCoordenadasOcorrenciasRaioUseCase(coordenadaTeste, 1.25))
            .thenReturn(Result.success(listOf(
                coordenadaTeste,
                coordenadaTeste,
                coordenadaTeste
            )))


        setupViewModel()
        viewModel.buscarOcorrenciasRaio()
        advanceUntilIdle()

        Assert.assertTrue(viewModel._uiState.value is MainStateHolder.Content)
    }

    @Test
    fun `Buscar Ocorrencias e retornar erro, estado deve virar error`()= runTest {
        whenever(conexaoUtils.observarConexao)
            .thenReturn(
                flowOf(false)
            )


        whenever(obterCoordenadasOcorrenciasRaioUseCase(coordenadaTeste, 1.25))
            .thenReturn(Result.failure(Exception("")))

        setupViewModel()
        viewModel.buscarOcorrenciasRaio()
        advanceUntilIdle()

        Assert.assertTrue(viewModel._uiState.value is MainStateHolder.Error)
    }

}