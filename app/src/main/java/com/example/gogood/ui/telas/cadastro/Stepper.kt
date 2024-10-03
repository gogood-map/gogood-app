package com.example.gogood.ui.telas.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Stepper(step: String) {
    val steps = listOf("Cadastro", "Dados Pessoais", "Concluído")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
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
                    .weight(5f)
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
                            modifier = Modifier.widthIn(max = 80.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                )
            }
            if (currentStep != steps.last()) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview
@Composable
fun StepperPreview(modifier: Modifier = Modifier) {
    Stepper("Dados Pessoais")
}