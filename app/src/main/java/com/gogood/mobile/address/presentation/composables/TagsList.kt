package com.gogood.mobile.address.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun TagsList(
    label: String,
    tags: List<String>,
    state: MutableState<String>,
    modifier: Modifier = Modifier.padding(5.dp, 10.dp, 5.dp, 10.dp)
) {

    Text(
        color = Color.Black,
        text = label,
        fontSize = 16.sp,  // Aumentando o tamanho da fonte
        fontWeight = FontWeight.Bold
    )

    LazyRow(
        modifier = modifier
            .background(color = Color.White)
    ) {


        items(tags.size) { index ->
            Tag(
                label = tags[index],
                onClick = {
                    state.value = tags[index]
                },
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
fun TagListPreview(modifier: Modifier = Modifier) {
    var state = remember { mutableStateOf("Casa") }
    TagsList(
        tags = listOf("Casa", "Trabalho", "Escola", "Outro"),
        state = state,
        label = "Etiqueta (Opcional)",
        modifier = modifier
    )
}