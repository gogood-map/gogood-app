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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.auth.apresentation.viewmodels.LoginViewModel
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GogoodBorderWhite
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodOptionRed
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CaixaPesquisaEndereco(
    modifier: Modifier = Modifier,
    searchState: MutableState<String>,
    onDone: () -> Unit,
) {
    var corBorda by remember{
        mutableStateOf(GogoodBorderWhite)
    }
    var caixaAtiva by remember {
        mutableStateOf(false)
    }
    val mapaViewModel = koinViewModel<MapaViewModel>()
    val loginViewModel = koinViewModel<LoginViewModel>()
    Column {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier= Modifier
                .height(45.dp)
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(32.dp))
                .border(1.dp, corBorda, RoundedCornerShape(32.dp))
        ){
            BasicTextField(
                value = searchState.value,
                onValueChange = {
                    searchState.value = it
                },
                modifier = modifier
                    .fillMaxWidth(.85f)
                    .height(45.dp)
                    .border(.50.dp, Color.Transparent, RoundedCornerShape(32.dp))
                    .background(Color.White, RoundedCornerShape(32.dp))
                    .padding(start = 15.dp, top = 15.dp)
                    .onFocusChanged { estadoFoco ->
                        mapaViewModel.salvarEnderecoProcurado.value = true
                        caixaAtiva = estadoFoco.isFocused

                    },
                singleLine = true,

                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if(searchState.value.isNotEmpty()){
                            corBorda = GogoodGray
                            onDone()
                        }else{
                            corBorda = GogoodOptionRed
                        }

                    }
                ),
                decorationBox = {innerTextField->
                    if(searchState.value.isEmpty()){
                        Text(
                            text = "Procure por um endereço",
                            color = Color.Gray, fontSize = 16.sp)

                    }
                    innerTextField()

                }
            )
            Icon(
                modifier = Modifier.clickable {
                    if(searchState.value.isNotEmpty()){
                        corBorda = GogoodBorderWhite
                        onDone()
                    }else{
                        corBorda = GogoodOptionRed
                    }
                },
                imageVector = Icons.Default.Search, contentDescription = "Pesquisar endereço")
        }
        if(caixaAtiva){
            if(loginViewModel.isLoggedIn.value){
                Spacer(modifier = Modifier.height(8.dp))
                BarraEnderecosFavoritos {
                    mapaViewModel.salvarEnderecoProcurado.value = false
                    searchState.value = it
                }
        }
            Spacer(modifier = Modifier.height(8.dp))
            BuscasRecentes(tipo = TipoBuscaRecente.ENDERECO){
                mapaViewModel.salvarEnderecoProcurado.value = false
                searchState.value = it
            }
        }

    }





}






