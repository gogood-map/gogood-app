package com.example.gogood.graficos

import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.gogood.ui.theme.GoGoodTheme

@Composable
fun Grafico(){
   var src="https://app.powerbi.com/view?r=eyJrIjoiOTJlNjA3MTMtODM3Yy00ZTRjLWIzYjQtYzM4YmYyMDZhMjk1IiwidCI6ImZkNTBiNDU3LTg0ZTAtNDAwYy04MGYyLTQ2MGYyOGViNDFhNiIsImMiOjR9&pageName=b0249758e9bd0b41ed04"
    AndroidView(modifier= Modifier.height(300.dp) ,factory = {
        WebView(it).apply {
            settings.javaScriptEnabled = true

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            loadUrl(src)
        }
    }, update = {
        it.loadDataWithBaseURL(null, src, "text/html", "utf-8", null)
        it.loadUrl(src)
    })

}

@Preview
@Composable
fun GraficoView(){
    GoGoodTheme {
        Grafico()
    }
}