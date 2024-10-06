package com.example.gogood.ui.telas.cadastro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gogood.R

@Composable
fun ConclusaoArte() {
    Image(
        painter = painterResource(id = R.drawable.illustration), // Altere para o nome correto do arquivo
        contentDescription = "Conclusao",
        modifier = Modifier.size(280.dp),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PreviewConclusaoArte() {
    ConclusaoArte()
}
