package com.gogood.mobile.common.apresentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gogood.mobile.ui.theme.GoGoodTheme
import com.gogood.mobile.ui.theme.GogoodOptionRed
import com.gogood.mobile.ui.theme.GogoodOptionYellow

@Composable
fun Erro(modifier: Modifier = Modifier,
         titulo: String, descricao: String,
         aoSairDialog: ()->Unit
    ) {
    Column(modifier=modifier.fillMaxSize()) {
        AlertDialog(
            title= {
                Text(text = titulo)
            },
            text = {
                Text(text = descricao)
            },

            icon = {
                Icon(
                    tint = GogoodOptionRed,
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "Ícone erro"
                )
            },
            onDismissRequest = {
                    aoSairDialog()
            },
            confirmButton = {
                TextButton(onClick = {
                    aoSairDialog()
                }) {
                Text(text = "Fechar")
            } }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErroPreview() {
    GoGoodTheme {
        Erro(titulo = "Deu erro", descricao = "Erro desconhecido"){

        }
    }
}