package com.gogood.mobile.address.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gogood.mobile.address.domain.model.AddressRequest
import com.gogood.mobile.address.presentation.viewModel.AddressViewModel
import com.gogood.mobile.auth.apresentation.composables.Campo
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddressForm(
    onSubmit: (AddressRequest) -> Unit,
    modifier: Modifier = Modifier
) {
    val addressViewModel: AddressViewModel = koinViewModel()

    val cep = remember { mutableStateOf(addressViewModel.addressRequest.cep) }
    val rua = remember { mutableStateOf(addressViewModel.addressRequest.rua) }
    val cidade = remember { mutableStateOf(addressViewModel.addressRequest.cidade) }
    val numero = remember { mutableStateOf(addressViewModel.addressRequest.numero) }
    val bairro = remember { mutableStateOf(addressViewModel.addressRequest.bairro) }
    val tipoEndereco = remember { mutableStateOf(addressViewModel.addressRequest.tipoEndereco) }

    Column( modifier = modifier ) {
        Text(
            text = "Endereço",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Campo("CEP", state = cep, { it.length == 8 })
        Campo("Rua", state = rua, { it.isNotBlank() })
        Campo("Cidade", state = cidade, { it.isNotBlank() })
        Campo("Número", state = numero, { it.isNotBlank() })
        Campo("Bairro", state = bairro, { it.isNotBlank() })
        TagsList(
            tags = listOf("Casa", "Trabalho", "Faculdade", "Parceiro(a)", "Outro"),
            state = tipoEndereco
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                addressViewModel.addressRequest = AddressRequest(
                    cep = cep.value,
                    rua = rua.value,
                    cidade = cidade.value,
                    numero = numero.value,
                    bairro = bairro.value,
                    tipoEndereco = tipoEndereco.value
                )
                onSubmit(addressViewModel.addressRequest)
            },
            enabled = cep.value.length == 8 && rua.value.isNotBlank() && cidade.value.isNotBlank() && numero.value.isNotBlank() && bairro.value.isNotBlank()
        ) {
            Text("Cadastrar")
        }
    }
}

@Preview
@Composable
fun AddressFormPreview(modifier: Modifier = Modifier) {
    AddressForm(
        onSubmit = {},
        modifier = modifier
    )
}