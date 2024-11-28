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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodOptionRed

@Composable
fun CaixaPesquisaRota(
    modifier: Modifier = Modifier,
    origem: MutableState<String>,
    destino: MutableState<String>,
    onDone: () -> Unit,
) {
    var bordaOrigem by remember {
        mutableStateOf(GogoodGray)
    }
    var bordaDestino by remember {
        mutableStateOf(GogoodGray)
    }
    Column(Modifier.fillMaxWidth()) {
        BasicTextField(
            value = origem.value,
            onValueChange = {
                origem.value = it
            },
            modifier = modifier
                .fillMaxWidth()
                .height(45.dp)
                .border(.50.dp, bordaOrigem, RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                .background(Color.White, RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp))
                .padding(start = 15.dp, top = 15.dp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier= Modifier
                .height(45.dp)
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .border(
                    .50.dp,
                    bordaDestino,
                    RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
        ){
        BasicTextField(
                value = destino.value,
                onValueChange = {
                    destino.value = it
                },
                modifier = modifier
                    .fillMaxWidth(.85f)
                    .height(45.dp)
                    .border(
                        .50.dp,
                        Color.Transparent,
                        RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .background(
                        Color.White,
                        RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .padding(start = 15.dp, top = 15.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if(origem.value.isBlank()){
                            bordaOrigem = GogoodOptionRed
                        }
                        if(destino.value.isBlank()){
                            bordaDestino = GogoodOptionRed
                        }
                        if(origem.value.isNotBlank() && destino.value.isNotBlank()){
                            bordaDestino = GogoodGray
                            bordaOrigem = GogoodGray
                            onDone()
                        }
                    }
                )
            )
            Icon(
                modifier = Modifier.clickable {
                    if(origem.value.isBlank()){
                        bordaOrigem = GogoodOptionRed
                    }
                    if(destino.value.isBlank()){
                        bordaDestino = GogoodOptionRed
                    }
                    if(origem.value.isNotBlank() && destino.value.isNotBlank()){
                        bordaDestino = GogoodGray
                        bordaOrigem = GogoodGray
                        onDone()
                    }

                },
                imageVector = Icons.Default.Search, contentDescription = "Pesquisar endereço")
        }


    }
}



