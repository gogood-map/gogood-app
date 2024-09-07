package com.example.gogood.ui.theme


import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Typography

import com.example.gogood.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",

    certificates = R.array.com_google_android_gms_fonts_certs // Definir o certificado corretamente
)


val poppinsFontFamily = FontFamily(
    Font(googleFont = GoogleFont("Poppins"), weight = FontWeight.Normal, fontProvider = provider),
    Font(googleFont = GoogleFont("Poppins"), weight = FontWeight.Bold, fontProvider = provider),
    Font(googleFont = GoogleFont("Poppins"), weight = FontWeight.Medium, fontProvider = provider)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    labelSmall = TextStyle(
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)
