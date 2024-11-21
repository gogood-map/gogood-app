package com.gogood.mobile.auth.apresentation.composables.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.auth.apresentation.viewmodels.CadastroViewModel
import com.gogood.mobile.auth.domain.models.UsuarioCadastroRequest
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EtapaConclusao(click: ()->Unit) {
    val cadastroViewModel: CadastroViewModel = koinViewModel()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        if(cadastroViewModel.isLoading){
            CircularProgressIndicator(
                color = GogoodGreen,
                modifier = Modifier
                    .size(80.dp)
            )
        }
        if(cadastroViewModel.isOk){
            ConcluidoTitle()
            ConcluidoMessage()
            ConcluidoArt()
            ConcluidoNavigationButtons(cadastroViewModel){
                click()
            }
        }
        if(cadastroViewModel.isError){

            ErrorTitle()
            Button(onClick = {
                cadastroViewModel.usuarioCadastro = UsuarioCadastroRequest()
                cadastroViewModel.currentSection.value = "CadastroSection"
            }) {
                Text(text = "Tentar novamente")
            }
        }

    }
}

@Composable
fun ConcluidoTitle() {
    Text(
        text = "Cadastro concluído",
        fontSize = 24.sp,
        fontWeight = FontWeight.Black,
    )
}

@Composable
fun ErrorTitle() {
    Text(
        text = "Cadastro não concluído",
        fontSize = 24.sp,
        fontWeight = FontWeight.Black,
    )
}

@Composable
fun ConcluidoMessage() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Obrigado por se juntar a nós. Sua \nsegurança é nossa prioridade.",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun ConcluidoArt() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConclusaoArte()
    }
}

@Composable
fun ConcluidoNavigationButtons(viewModel: CadastroViewModel, click: () -> Unit) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        IconButton(
            onClick = { click() },
            modifier = Modifier
                .size(50.dp)
                .shadow(8.dp, CircleShape)
                .background(GogoodGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}