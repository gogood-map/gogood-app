package com.gogood.mobile.common

import com.gogood.mobile.BuildConfig
import com.gogood.mobile.home.domain.services.MapsService
import com.gogood.mobile.auth.domain.services.UserService
import com.gogood.mobile.home.domain.services.GooglePlacesService
import com.gogood.mobile.menu.domain.services.EnderecoService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    class apiInterceptor: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val newRequest = Request.Builder()
                .url(request.url)
                .method(request.method, request.body)

                .build()
            return chain.proceed(newRequest)
        }
    }


    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor())
            .build()
    }

    private val clientGooglePlaces by lazy {
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

    private val apiGooglePlaces by lazy {
        Retrofit.Builder()
            .client(clientGooglePlaces)
            .baseUrl(BuildConfig.API_BASE_GOOGLE_PLACES)
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

    val mapsService by lazy {
        api.create(MapsService::class.java)
    }

    val enderecoService by lazy {
        api.create(EnderecoService::class.java)
    }

    val googlePlacesService by lazy {
        apiGooglePlaces.create(GooglePlacesService::class.java)
    }

    val addressService by lazy {
        api.create(EnderecoService::class.java)
    }

//    fun <T> createService(service: Class<T>): T {
//        return api.create(service)
//    }

}