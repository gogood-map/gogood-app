package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.gogood.mobile.ui.theme.GogoodGray

@Composable
fun BotaoMenu(modifier: Modifier = Modifier, click: ()->Unit){
    IconButton(
        onClick = click,
        modifier = modifier
            .size(32.dp)
            .shadow(8.dp, CircleShape)
            .background(shape = CircleShape, color = GogoodGray)
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Pesquisar",
            tint = androidx.compose.ui.graphics.Color.White
        )
    }
}