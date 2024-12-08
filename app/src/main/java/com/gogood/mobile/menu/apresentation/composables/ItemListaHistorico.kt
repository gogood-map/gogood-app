package com.gogood.mobile.menu.apresentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gogood.mobile.home.domain.models.RotaHistoricoResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ItemListaHistorico(modifier: Modifier = Modifier, historicoResponse: RotaHistoricoResponse,
                       onClick: ()->Unit) {
    Column (modifier=Modifier.clickable {
        onClick()
    }.fillMaxWidth()
        .height(90.dp)
        .padding(top = 10.dp)){
        Row (verticalAlignment = Alignment.CenterVertically){
            Icon(imageVector = Icons.Default.History, contentDescription = "Icone histórico")
            Text(text = formatarData(historicoResponse.created_at))
        }
        Column (modifier=Modifier.padding(horizontal = 20.dp)){
            Text(text = historicoResponse.origem)
            Text(text = historicoResponse.destino)
        }
    }
}


fun formatarData(data: String): String{

    val inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH:mm:ss", Locale("pt", "BR"))
    val dateTime = LocalDateTime.parse(data, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("d MMM 'de' yyyy", Locale("pt", "BR"))
    return dateTime.format(outputFormatter)

}