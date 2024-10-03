package com.example.gogood.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RotasViewModel: ViewModel() {
    var origem by mutableStateOf("")
        private set

    var destino by mutableStateOf("")
        private set

    var meioTransport by mutableStateOf("")
        private set

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl("https://eiwfenr6gmx3eaarbxu2yppzaq0miymr.lambda-url.us-east-1.on.aws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}