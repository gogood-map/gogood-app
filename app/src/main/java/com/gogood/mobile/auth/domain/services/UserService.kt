package com.gogood.mobile.auth.domain.services

import com.gogood.mobile.auth.domain.models.UsuarioCadastroRequest
import com.gogood.mobile.auth.domain.models.UsuarioResponse
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
    ): Response<UsuarioResponse>

    @POST("/usuarios/cadastro")
    suspend fun register(
        @Body usuarioCadastro: UsuarioCadastroRequest
    ): Response<UsuarioResponse>

    @PUT("/usuarios/{id}")
    suspend fun update(
        @Path("id") id: Int,
        @Body usuarioCadastro: UsuarioCadastroRequest
    ): Response<Void>

    @DELETE("/usuarios/{id}")
    suspend fun delete(
        @Path("id") id: Int
    ): Response<Void>

    @GET("/usuarios/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): Response<UsuarioCadastroRequest>

}

data class LoginRequest(
    val email: String,
    val senha: String
)