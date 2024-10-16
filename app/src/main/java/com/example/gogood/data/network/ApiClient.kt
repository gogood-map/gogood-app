package com.example.gogood.data.network

import com.example.gogood.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    class apiInterceptor: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val newRequest = Request.Builder()
                .url(request.url)
                .method(request.method, request.body)
//                .header("Authorization", "")
                .build()
            return chain.proceed(newRequest)
        }
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(apiInterceptor())
            .addInterceptor(logInterceptor())
            .build()
    }

    private val api by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun logInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    val userService by lazy {
        api.create(UserService::class.java)
    }

//    fun <T> createService(service: Class<T>): T {
//        return api.create(service)
//    }

}