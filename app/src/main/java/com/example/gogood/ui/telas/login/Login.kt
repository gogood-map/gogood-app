package com.example.gogood.login

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gogood.ui.theme.GoGoodTheme
import com.example.gogood.ui.theme.GogoodGray
import com.example.gogood.ui.theme.GogoodGreen
import com.example.gogood.ui.theme.GogoodWhite

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoGoodTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginApp(modifier: Modifier = Modifier) {
    val emailState = remember { mutableStateOf("") }
    val senhaState = remember { mutableStateOf("") }

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
                .padding(horizontal = 16.dp, vertical = 70.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(16.dp),
                        spotColor = Color.Black.copy(alpha = 0.5f) // Decrease opacity here
                    )
                    .background(Color.White, RoundedCornerShape(16.dp))
            ) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Black,
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Email*",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(bottom = 4.dp)
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
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Senha*",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(bottom = 4.dp)
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
                            onClick = { /* TODO: Ação ao clicar */ },
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
                        Column (horizontalAlignment = Alignment.CenterHorizontally) {
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
                                append("É novo na nossa plataforma? ")
                                withStyle(style = SpanStyle(color = GogoodGreen, textDecoration = TextDecoration.Underline)) {
                                    append("Cadastrar")
                                }
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                        )
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
        LoginApp()
    }
}
