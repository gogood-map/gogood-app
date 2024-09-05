package com.example.gogood.cadastro

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gogood.ui.theme.GoGoodTheme
import com.example.gogood.ui.theme.GogoodGray
import com.example.gogood.ui.theme.GogoodGreen
import com.example.gogood.ui.theme.GogoodWhite

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroApp(modifier: Modifier = Modifier) {
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
                    .height(620.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(16.dp),
                        spotColor = Color.Black.copy(alpha = 0.5f) // Decrease opacity here
                    )
                    .background(Color.White, RoundedCornerShape(16.dp))
            ) {
                Column {
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            BasicText(
                                text = "Cadastro",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier.padding(bottom = 2.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .width(65.dp)
                                    .height(8.dp)
                                    .shadow(
                                        13.dp,
                                        RoundedCornerShape(50),
                                        ambientColor = Color(0xFF00FF99).copy(alpha = 0.8f),
                                        spotColor = Color(0xFF00C9A7).copy(alpha = 0.8f)
                                    )
                                    .background(
                                        Brush.horizontalGradient(
                                            listOf(
                                                Color(0xFF00FF99),
                                                Color(0xFF00C9A7)
                                            )
                                        ), shape = RoundedCornerShape(4.dp)
                                    )
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            BasicText(
                                text = "",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .width(65.dp)
                                    .height(8.dp)
                                    .background(Color(0xFFCCCCCC), shape = RoundedCornerShape(4.dp))
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            BasicText(
                                text = "",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .width(65.dp)
                                    .height(8.dp)
                                    .background(Color(0xFFCCCCCC), shape = RoundedCornerShape(4.dp))
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            BasicText(
                                text = "",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .width(65.dp)
                                    .height(8.dp)
                                    .background(Color(0xFFCCCCCC), shape = RoundedCornerShape(4.dp))
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(28.dp))
                    DadosPessoaisSection()
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
fun CadastroSection() {
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
fun DadosPessoaisSection() {
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
                .padding(top = 14.dp, start = 24.dp, end = 24.dp),
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
                BasicText(text = "Prefiro não dizer", style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal))
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
                BasicText(text = "Outro", style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal))

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
            onClick = { },
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