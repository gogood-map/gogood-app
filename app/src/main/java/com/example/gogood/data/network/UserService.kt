package com.example.gogood.data.network

import com.example.gogood.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {

    @POST("/usuarios/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<User>

    @POST("/usuarios/cadastro")
    suspend fun register(
        @Body user: User
    ): Response<User>

    @PUT("/usuarios/{id}")
    suspend fun update(
        @Path("id") id: Int,
        @Body user: User
    ): Response<Void>

    @DELETE("/usuarios/{id}")
    suspend fun delete(
        @Path("id") id: Int
    ): Response<Void>

    @GET("/usuarios/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): Response<User>

}

data class LoginRequest(
    val email: String,
    val senha: String
)