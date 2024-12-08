package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GogoodBorderWhite
import com.gogood.mobile.ui.theme.GogoodWhite
import org.koin.compose.viewmodel.koinViewModel


enum class TipoBuscaRecente{
    ENDERECO,
    ROTA
}

@Composable
fun BuscasRecentes(modifier: Modifier = Modifier, tipo:TipoBuscaRecente, onClick: (String) -> Unit) {
    val mapaViewModel = koinViewModel<MapaViewModel>()
    if(tipo == TipoBuscaRecente.ENDERECO){
        mapaViewModel.obterEnderecosPesquisadosRecentes()
        val lista = mapaViewModel.enderecosRecentesPesquisados.collectAsState()
        if(lista.value.isNotEmpty()){
            LazyColumn(modifier = modifier
                .fillMaxWidth()
                .height(152.dp)

                .border(.75.dp, GogoodBorderWhite, RoundedCornerShape(28.dp))
                .background(Color.White, RoundedCornerShape(28.dp))
            )
            {
                itemsIndexed(lista.value){i, it->
                    Column (
                        modifier= Modifier.padding(start = 16.dp, end = 16.dp).clickable {
                            onClick(it)
                        },
                    ){
                        Spacer(modifier = Modifier.height(16.dp))
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            Icon(imageVector = Icons.Default.Schedule, contentDescription = "Recente")
                            Text(text = it)

                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        if(i<lista.value.size-1){
                            HorizontalDivider(thickness = 2.dp)
                        }else{
                            Spacer(modifier = Modifier.height(8.dp))
                        }


                    }



                }
            }
        }

    }
}

