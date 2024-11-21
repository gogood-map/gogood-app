package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gogood.mobile.ui.theme.GoGoodTheme
import com.gogood.mobile.ui.theme.GogoodGreen

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Column (modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        CircularProgressIndicator(
            color = GogoodGreen,
            modifier = Modifier
                .size(80.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingPreview() {
    GoGoodTheme {
        Loading()
    }
}