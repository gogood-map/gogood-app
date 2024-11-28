package com.gogood.mobile.home.presentation.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.ui.theme.GogoodBorderWhite
import com.gogood.mobile.ui.theme.GogoodGray

@Composable
fun CardRelatorio(modifier:Modifier=Modifier, titulo: String, subTitulo:String, corFundo: Color = GogoodBorderWhite){
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier= modifier
            .background(corFundo, RoundedCornerShape(16))
            .height(104.dp)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = titulo,
            fontSize = 28.sp,
            color = GogoodGray,
            fontWeight = FontWeight.SemiBold)
        Text(text = subTitulo,
            fontSize = 18.sp,
            color = GogoodGray,
            fontWeight = FontWeight.Medium)
    }
}
@Composable
fun CardRelatorio(modifier:Modifier=Modifier, titulo: String, subTitulo:String, corFundo: Brush){
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier= modifier
            .background(corFundo, RoundedCornerShape(16))
            .height(108.dp)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = titulo,
            fontSize = 28.sp,
            color = GogoodGray,
            fontWeight = FontWeight.SemiBold)
        Text(text = subTitulo,
            fontSize = 18.sp,
            color = GogoodGray,
            fontWeight = FontWeight.Medium)
    }
}