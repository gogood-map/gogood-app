package com.gogood.mobile.auth.data.repository

import com.gogood.mobile.auth.domain.models.UsuarioCadastroRequest
import com.gogood.mobile.auth.domain.models.UsuarioResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IUserRepository {
    suspend fun login(email: String, password: String): Response<UsuarioCadastroRequest>
    suspend fun register(usuarioCadastro: UsuarioCadastroRequest): Response<UsuarioResponse>
    suspend fun update(usuarioCadastro: UsuarioCadastroRequest): Response<Void>
    suspend fun delete(id: Int): Response<Void>
    suspend fun getUser(userId: String): Response<UsuarioCadastroRequest>
    suspend fun salvarUsuario(usuarioResponse: UsuarioResponse)
    fun obterUsuarioSalvo(): Flow<UsuarioResponse?>
//    suspend fun googleRegister(user: User): Response<User>
//    suspend fun googleLogin(email: String, googleId: String): Response<User>
}