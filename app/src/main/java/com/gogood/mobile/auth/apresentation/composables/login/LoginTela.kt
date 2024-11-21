package com.gogood.mobile.auth.apresentation.composables.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gogood.mobile.ui.theme.GoGoodTheme
import com.gogood.mobile.ui.theme.GogoodWhite

@Composable
fun LoginTela(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GogoodWhite)
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(50.dp)
                    .shadow(8.dp, CircleShape)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

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
                    .height(436.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(16.dp),
                        spotColor = Color.Black.copy(alpha = 0.5f)
                    )
                    .background(Color.White, RoundedCornerShape(16.dp))
            ) {
                LoginSection(navController)
            }
        }
        Spacer(modifier = Modifier.weight(2f))
    }

}

@Preview
@Composable
fun LoginTelaPreview(modifier: Modifier = Modifier) {
    GoGoodTheme {
        LoginTela(navController = rememberNavController(), modifier = modifier)
    }
}