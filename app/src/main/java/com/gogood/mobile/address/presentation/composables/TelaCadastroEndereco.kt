package com.gogood.mobile.address.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gogood.mobile.address.presentation.viewModel.AddressViewModel

@Composable
fun TelaCadastroEndereco(
    navController: NavController,
    onClose: () -> Unit,
    addressViewModel: AddressViewModel,
    userId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.End)
        ) {
            IconButton(
                onClick = { onClose() },
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.Transparent)
            ) {
                Icon(
                    Icons.Sharp.Close,
                    contentDescription = "Fechar Cadastro de Endereço",
                    modifier = Modifier
                        .size(30.dp)
                )
            }

            Text(
                text = "Endereço",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }

    }
}