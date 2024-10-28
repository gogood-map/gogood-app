package com.gogood.mobile.auth.apresentation.composables.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gogood.mobile.ui.theme.GogoodWhite
import androidx.navigation.compose.rememberNavController
import com.gogood.mobile.auth.apresentation.composables.Stepper
import com.gogood.mobile.auth.apresentation.viewmodels.CadastroViewModel
import com.gogood.mobile.auth.apresentation.viewmodels.SectionViewModel
import com.gogood.mobile.ui.theme.GoGoodTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CadastroTela(navController: NavController, modifier: Modifier = Modifier) {
    val viewModelSection = koinViewModel<SectionViewModel>()
    val cadastroViewModel = koinViewModel<CadastroViewModel>()
    val currentSection by viewModelSection.currentSection.collectAsState()
    val boxHeight by viewModelSection.boxHeight.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GogoodWhite)
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(50.dp)
                    .shadow(8.dp, CircleShape)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 40.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(boxHeight)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(16.dp),
                        spotColor = Color.Black.copy(alpha = 0.5f)
                    )
                    .background(Color.White, RoundedCornerShape(16.dp))
            ) {
                Column {
                    Spacer(modifier = Modifier.height(30.dp))

                    when (currentSection) {
                        "CadastroSection" -> {
                            Stepper("Cadastro")
                            Spacer(modifier = Modifier.height(28.dp))
                            CadastroSection(viewModelSection)
                        }
                        "DadosPessoaisSection" -> {
                            Stepper("Dados Pessoais")
                            Spacer(modifier = Modifier.height(28.dp))
                            DadosPessoaisSection(viewModelSection = viewModelSection)
                        }
                        "ConcluidoSection" -> {
                            Stepper("Finalização")
                            Spacer(modifier = Modifier.height(28.dp))
                            ConcluidoSection(viewModelSection){
                                navController.navigate("Mapa")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CadastroTelaPreview(modifier: Modifier = Modifier) {
    GoGoodTheme {
        CadastroTela(navController = rememberNavController(), modifier = modifier)
    }
}