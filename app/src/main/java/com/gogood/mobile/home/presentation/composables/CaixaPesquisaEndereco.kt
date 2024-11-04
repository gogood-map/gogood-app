package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.gogood.mobile.ui.theme.GogoodGray

@Composable
fun CaixaPesquisaEndereco(
    searchState: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier
) {

            BasicTextField(
                value = searchState,
                onValueChange = onValueChange,
                modifier = modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .border(.50.dp, GogoodGray, RoundedCornerShape(32.dp))
                    .background(Color.White, RoundedCornerShape(32.dp))
                    .padding(start = 15.dp, top = 15.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { onDone() }
                )
            )
        }




