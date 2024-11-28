package com.gogood.mobile.address.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TagsList(
    tags: List<String>,
    state: MutableState<String>,
    modifier: Modifier = Modifier
) {
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
        modifier = modifier
    )
}