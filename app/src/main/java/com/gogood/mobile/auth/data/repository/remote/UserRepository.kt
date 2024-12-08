package com.gogood.mobile.auth.data.repository.remote

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.gogood.mobile.auth.domain.services.UserService
import com.gogood.mobile.auth.domain.models.UsuarioCadastroRequest
import com.gogood.mobile.auth.data.repository.IUserRepository
import com.gogood.mobile.auth.domain.models.UsuarioResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import retrofit2.Response

class UserRepository(private val userService: UserService, private val dataStore: DataStore<Preferences>): IUserRepository {
    val usuarioPreferencesKey = stringPreferencesKey("usuario")
    override suspend fun delete(id: Int): Response<Void> {
        return userService.delete(id)
    }

    override suspend fun getUser(userId: String): Response<UsuarioCadastroRequest> {
        TODO("Not yet implemented")
    }

    override suspend fun register(usuarioCadastro: UsuarioCadastroRequest): Response<UsuarioResponse> {
        return userService.register(usuarioCadastro)
    }

    override suspend fun update(usuarioCadastro: UsuarioCadastroRequest): Response<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): Response<UsuarioCadastroRequest> {
        TODO("Not yet implemented")
    }

    override suspend fun salvarUsuarioLocal(usuarioResponse: UsuarioResponse) {
        val jsonString = Json.encodeToString(usuarioResponse)
        dataStore.edit { preferences ->
            preferences[usuarioPreferencesKey] = jsonString
        }
    }
    override fun obterUsuarioSalvoLocal(): Flow<UsuarioResponse?> {
        return dataStore.data.map { preferences ->
            preferences[usuarioPreferencesKey]?.let { jsonString ->
                Json.decodeFromString<UsuarioResponse>(jsonString)
            }
        }
    }
    override suspend fun excluirUsuarioLocal(){
        dataStore.edit { preferences ->
            preferences.remove(usuarioPreferencesKey)
        }
    }
}