package com.gogood.mobile.auth.apresentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gogood.mobile.R

@Composable
fun GoogleIcon() {
    Image(
        painter = painterResource(id = R.drawable.googleicon),
        contentDescription = "Google Icon",
        modifier = Modifier.size(20.dp),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun PreviewGoogleIcon() {
    GoogleIcon()
}
