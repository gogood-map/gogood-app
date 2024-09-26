package com.example.gogood.cadastro

import ConclusaoArte
import androidx.lifecycle.viewmodel.compose.viewModel
import GoogleIcon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogood.ui.theme.GoGoodTheme
import com.example.gogood.ui.theme.GogoodGray
import com.example.gogood.ui.theme.GogoodGreen
import com.example.gogood.ui.theme.GogoodWhite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Cadastro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoGoodTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CadastroApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

class SectionViewModel : ViewModel() {
    private val _boxHeight = MutableStateFlow(400.dp)
    val boxHeight: StateFlow<Dp> = _boxHeight
    val currentSection = MutableStateFlow("CadastroSection")

    init {
        viewModelScope.launch {
            currentSection.collect { section ->
                _boxHeight.value = when (section) {
                    "CadastroSection" -> 620.dp
                    "DadosPessoaisSection" -> 600.dp
                    "PersonalizacaoSection" -> 500.dp
                    "ConcluidoSection" -> 550.dp
                    else -> 400.dp
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroApp(modifier: Modifier = Modifier, viewModel: SectionViewModel = viewModel()) {
    val currentSection by viewModel.currentSection.collectAsState()
    val boxHeight by viewModel.boxHeight.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(GogoodWhite)
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            IconButton(
                onClick = { /* TODO: Ação ao clicar */ },
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
                        spotColor = Color.Black.copy(alpha = 0.5f) // Decrease opacity here
                    )
                    .background(Color.White, RoundedCornerShape(16.dp))
            ) {
                Column {
                    Spacer(modifier = Modifier.height(30.dp))

                    when (currentSection) {
                        "CadastroSection" -> {
                            Stepper("Cadastro")
                            Spacer(modifier = Modifier.height(28.dp))
                            CadastroSection(viewModel = viewModel)
                        }

                        "DadosPessoaisSection" -> {
                            Stepper("Dados Pessoais")
                            Spacer(modifier = Modifier.height(28.dp))
                            DadosPessoaisSection(viewModel = viewModel)
                        }

                        "PersonalizacaoSection" -> {
                            Stepper("Personalização")
                            Spacer(modifier = Modifier.height(28.dp))
                            PersonalizacaoSection(viewModel = viewModel)
                        }

                        "ConcluidoSection" -> {
                            Stepper("Concluído")
                            Spacer(modifier = Modifier.height(28.dp))
                            ConcluidoSection(viewModel = viewModel)
                        }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoGoodTheme {
        CadastroApp()
    }
}

@Composable
fun Stepper(step: String) {
    val steps = listOf("Cadastro", "Dados Pessoais", "Personalização", "Concluído")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp) // Ajuste a altura para acomodar o texto e as barras
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween, // Mantém o espaçamento entre os itens
        verticalAlignment = Alignment.CenterVertically // Centraliza verticalmente
    ) {
        steps.forEach { currentStep ->
            val isActiveStep = steps.indexOf(currentStep) <= steps.indexOf(step)
            val backgroundColor = if (isActiveStep) {
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF00FF99), Color(0xFF00C9A7))
                )
            } else {
                Brush.linearGradient(colors = listOf(Color(0xFFCCCCCC), Color(0xFFCCCCCC)))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier.height(16.dp)
                ) {
                    if (currentStep == step) {
                        BasicText(
                            text = currentStep,
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (isActiveStep) Color(0xFF00FF99) else Color(0xFFCCCCCC)
                            ),
                            modifier = Modifier
                                .widthIn(max = 80.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }


                Box(
                    modifier = Modifier
                        .width(65.dp)
                        .height(8.dp)
                        .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                )
            }

        }
    }
}


@Composable
fun CadastroSection(viewModel: SectionViewModel) {
    val emailState = remember { mutableStateOf("") }
    val senhaState = remember { mutableStateOf("") }
    val confirmarSenhaState = remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Cadastre-se",
            fontSize = 30.sp,
            fontWeight = FontWeight.Black,
        )
    }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Email*",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                BasicTextField(
                    value = emailState.value,
                    onValueChange = { emailState.value = it },
                    modifier = Modifier
                        .width(250.dp)
                        .height(45.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(start = 15.dp, top = 15.dp),
                    singleLine = true,
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Senha*",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
                BasicTextField(
                    value = senhaState.value,
                    onValueChange = { senhaState.value = it },
                    modifier = Modifier
                        .width(250.dp)
                        .height(45.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(start = 15.dp, top = 15.dp),
                    singleLine = true,
                )

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Confirmar senha*",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
                BasicTextField(
                    value = confirmarSenhaState.value,
                    onValueChange = { confirmarSenhaState.value = it },
                    modifier = Modifier
                        .width(250.dp)
                        .height(45.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(start = 15.dp, top = 15.dp),
                    singleLine = true,
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        IconButton(
            onClick = {
                viewModel.currentSection.value = "DadosPessoaisSection"
            },
            modifier = Modifier
                .size(50.dp)
                .shadow(8.dp, CircleShape)
                .background(GogoodGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
    Spacer(modifier = Modifier.height(18.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Ou faça parte com",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            )
            Spacer(modifier = Modifier.height(10.dp))
            GoogleIcon()
        }

    }

    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            buildAnnotatedString {
                append("Já tem cadastro? Retome sua \n")
                append("segurança! ")
                withStyle(
                    style = SpanStyle(
                        color = GogoodGreen,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Login")
                }
            },
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DadosPessoaisSection(viewModel: SectionViewModel) {
    var selectedDate by remember { mutableStateOf("") }


    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Dados pessoais",
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
        )
    }
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 24.dp, end = 24.dp),
        ) {
            BasicText(
                text = "Selecione sua identidade de gênero:",
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(-20.dp) // Ajusta o espaçamento entre os itens
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(-2.dp)
            ) {
                RadioButton(
                    modifier = Modifier.padding(start = 9.dp),
                    colors = RadioButtonColors(
                        selectedColor = GogoodGreen,
                        unselectedColor = Color.Transparent,
                        disabledSelectedColor = Color.Transparent,
                        disabledUnselectedColor = Color.Transparent
                    ),
                    selected = true,
                    onClick = { /*TODO*/ })
                BasicText(
                    text = "Feminino",
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(-2.dp)
            ) {
                RadioButton(
                    modifier = Modifier.padding(start = 9.dp),
                    colors = RadioButtonColors(
                        selectedColor = GogoodGreen,
                        unselectedColor = Color(0xcFFC4D1E2),
                        disabledSelectedColor = Color.Transparent,
                        disabledUnselectedColor = Color.Transparent
                    ),
                    selected = false,
                    onClick = { /*TODO*/ })
                BasicText(
                    text = "Masculino",
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(-2.dp)
            ) {
                RadioButton(
                    modifier = Modifier.padding(start = 9.dp),
                    colors = RadioButtonColors(
                        selectedColor = GogoodGreen,
                        unselectedColor = Color(0xcFFC4D1E2),
                        disabledSelectedColor = Color.Transparent,
                        disabledUnselectedColor = Color.Transparent
                    ),
                    selected = false,
                    onClick = { /*TODO*/ })
                BasicText(
                    text = "Prefiro não dizer",
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(-2.dp)
            ) {
                RadioButton(
                    modifier = Modifier.padding(start = 9.dp),
                    colors = RadioButtonColors(
                        selectedColor = GogoodGreen,
                        unselectedColor = Color(0xcFFC4D1E2),
                        disabledSelectedColor = Color.Transparent,
                        disabledUnselectedColor = Color.Transparent
                    ),
                    selected = false,
                    onClick = { /*TODO*/ })
                BasicText(
                    text = "Outro",
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
                )

            }
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 25.dp)
    ) {
        Text(
            text = "Data de Nascimento",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        DatePickerInput(
            label = "dd/mm/yyyy",
            selectedDate = selectedDate,
            onDateSelected = { date -> selectedDate = date },
            modifier = Modifier
                .height(45.dp)
                .width(170.dp)

        )
    }
    Spacer(modifier = Modifier.height(14.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        IconButton(
            onClick = {
                viewModel.currentSection.value = "CadastroSection"
            },
            modifier = Modifier
                .size(50.dp)
                .shadow(8.dp, CircleShape)
                .background(GogoodGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        IconButton(
            onClick = {
                viewModel.currentSection.value = "PersonalizacaoSection"
            },
            modifier = Modifier
                .size(50.dp)
                .shadow(8.dp, CircleShape)
                .background(GogoodGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
    Spacer(modifier = Modifier.height(18.dp))

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Ou faça parte com",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            )
            Spacer(modifier = Modifier.height(10.dp))
            GoogleIcon()
        }

    }

    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            buildAnnotatedString {
                append("Já tem cadastro? Retome sua \n")
                append("segurança! ")
                withStyle(
                    style = SpanStyle(
                        color = GogoodGreen,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Login")
                }
            },
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun PersonalizacaoSection(viewModel: SectionViewModel) {
    val enderecoRemember = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Personalização",
            fontSize = 30.sp,
            fontWeight = FontWeight.Black,
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                buildAnnotatedString {
                    append("Estamos quase lá! Só mais \n")
                    append("algumas informações... ")
                },
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
            )
        }
    }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Seu endereço:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                BasicTextField(
                    value = enderecoRemember.value,
                    onValueChange = { enderecoRemember.value = it },
                    modifier = Modifier
                        .width(250.dp)
                        .height(45.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(start = 15.dp, top = 15.dp),
                    singleLine = true,
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        IconButton(
            onClick = {
                viewModel.currentSection.value = "DadosPessoaisSection"
            },
            modifier = Modifier
                .size(50.dp)
                .shadow(8.dp, CircleShape)
                .background(GogoodGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        IconButton(
            onClick = {
                viewModel.currentSection.value = "ConcluidoSection"
            },
            modifier = Modifier
                .size(50.dp)
                .shadow(8.dp, CircleShape)
                .background(GogoodGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
    Spacer(modifier = Modifier.height(18.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Ou faça parte com",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            )
            Spacer(modifier = Modifier.height(10.dp))
            GoogleIcon()
        }

    }

    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            buildAnnotatedString {
                append("Já tem cadastro? Retome sua \n")
                append("segurança! ")
                withStyle(
                    style = SpanStyle(
                        color = GogoodGreen,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Login")
                }
            },
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun ConcluidoSection(viewModel: SectionViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Cadastro concluído",
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                buildAnnotatedString {
                    append("Obrigado por se juntar a nós. Sua  \n")
                    append("segurança é nossa prioridade.")
                },
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
            )
        }
    }
    Column {
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
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(50.dp)
                .shadow(8.dp, CircleShape)
                .background(GogoodGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}