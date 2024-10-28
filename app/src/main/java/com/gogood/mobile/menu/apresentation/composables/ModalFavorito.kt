package com.gogood.mobile.menu.apresentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.gogood.mobile.ui.theme.CianoButton
import com.gogood.mobile.ui.theme.CinzaFont

@Composable
fun ModalFavorito(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .width(366.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {


            Text(
                text = "Adicionar favorito",
                modifier = Modifier.padding(bottom = 16.dp),
                style = TextStyle(fontSize = 24.sp), fontWeight = FontWeight(500), color = CinzaFont
            )

            val (endereco, setEndereco) = remember { mutableStateOf("") }
            val (tipo, setTipo) = remember { mutableStateOf("Casa") }


            TextField(
                value = endereco,
                onValueChange = setEndereco,
                label = { Text("Endereço*") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textStyle = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            DynamicSelectTextField(
                selectedValue = tipo,
                options = listOf("Casa", "Escritório", "Parceiro(a)"),
                label = "Tipo (Opcional)",
                onValueChangedEvent = setTipo,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = CianoButton,
                    contentColor = Color.White
                ),

                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(51.88.dp)
            )
            {
                Text("Adicionar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }


        }
    }
}