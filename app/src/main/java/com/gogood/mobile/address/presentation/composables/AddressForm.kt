import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.address.domain.model.AddressRequest
import com.gogood.mobile.address.presentation.composables.TagsList
import com.gogood.mobile.address.presentation.viewModel.AddressViewModel
import com.gogood.mobile.auth.apresentation.composables.CampoFavorito
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

    Column(
        modifier = modifier
            .background(Color.White)
            .padding(24.dp)
            .width(390.dp)
            .height(810.dp)
            .padding(top = 34.dp)
    ) {
        Text(
            text = "Favoritos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        CampoFavorito(
            "CEP", state = cep, { it.length == 8 }
        )
        CampoFavorito(
            "Rua", state = rua, { it.isNotBlank() }
        )
        CampoFavorito(
            "Cidade", state = cidade, { it.isNotBlank() }
        )
        CampoFavorito(
            "Número", state = numero, { it.isNotBlank() }
        )
        CampoFavorito(
            "Bairro", state = bairro, { it.isNotBlank() }
        )
        TagsList(
            "Etiqueta (Opcional)",
            tags = listOf("Casa", "Trabalho", "Faculdade", "Parceiro(a)", "Outro"),
            state = tipoEndereco,

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
            enabled = cep.value.length == 8 && rua.value.isNotBlank() && cidade.value.isNotBlank() && numero.value.isNotBlank() && bairro.value.isNotBlank(),
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Adicionar", fontSize = 16.sp)
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
