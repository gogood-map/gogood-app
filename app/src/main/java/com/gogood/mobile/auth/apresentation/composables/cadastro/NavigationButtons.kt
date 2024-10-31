package com.gogood.mobile.auth.apresentation.composables.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gogood.mobile.auth.apresentation.viewmodels.CadastroViewModel
import com.gogood.mobile.ui.theme.GogoodGray
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavigationButtons(nextSection: String, lastSection: String? = null, enableNext: Boolean = true,
                      click: ()-> Unit = {}) {
    val cadastroViewModel: CadastroViewModel = koinViewModel()
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        if (lastSection != null) {
            IconButton(
                onClick = {
                    cadastroViewModel.currentSection.value = lastSection
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
            Spacer(modifier = Modifier.width(16.dp))
        }

        IconButton(
            onClick = {
                if(enableNext){
                    cadastroViewModel.currentSection.value = nextSection
                    click()
                }
            },
            modifier = Modifier
                .size(50.dp)
                .shadow(8.dp, CircleShape)
                .background(if (enableNext)
                                    GogoodGray
                                else
                                    Color(0xFF7C7C7C) , CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next",
                tint = Color.White
            )
        }
    }
}