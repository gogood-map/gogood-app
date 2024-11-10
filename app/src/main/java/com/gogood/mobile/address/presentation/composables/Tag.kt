package com.gogood.mobile.address.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.OtherHouses
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Tag(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, Color.Black),
    ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                when (label) {
                    "Casa" -> {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Casa",
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .height(24.dp)
                                .aspectRatio(1f)
                        )
                    }
                    "Trabalho" -> {
                        Icon(
                            imageVector = Icons.Default.WorkOutline,
                            contentDescription = "Trabalho",
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .height(24.dp)
                                .aspectRatio(1f)
                        )
                    }
                    "Parceiro(a)" -> {
                        Icon(
                            imageVector = Icons.Default.OtherHouses,
                            contentDescription = "Parceiro(a)",
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .height(24.dp)
                                .aspectRatio(1f)
                        )
                    }
                    "Faculdade" -> {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "Faculdade",
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .height(24.dp)
                                .aspectRatio(1f)
                        )
                    }
                    "Outro" -> {
                        Icon(
                            imageVector = Icons.Default.LocalOffer,
                            contentDescription = "Outro",
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .height(24.dp)
                                .aspectRatio(1f)
                        )
                    }
                    else -> {
                        Icon(
                            imageVector = Icons.Default.LocalOffer,
                            contentDescription = "Outro",
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .height(24.dp)
                                .aspectRatio(1f)
                        )
                    }

                }
                Text(
                    text = label,
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                )
            }

    }
}

@Preview
@Composable
fun TagPreview(modifier: Modifier = Modifier) {
    Tag(
        label = "Faculdade",
        onClick = {},
        modifier = modifier
    )
}