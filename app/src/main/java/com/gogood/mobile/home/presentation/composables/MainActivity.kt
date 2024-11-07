package com.gogood.mobile.home.presentation.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gogood.mobile.common.di.appModule
import com.gogood.mobile.ui.theme.GoGoodTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }


        setContent {
            GoGoodTheme {
                AppNavegacao()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GoGoodTheme {
        AppNavegacao()
    }
}
