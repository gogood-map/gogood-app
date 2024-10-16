package com.example.gogood.domain.repository

import com.example.gogood.domain.model.User
import retrofit2.Response

interface IUserRepository {
    suspend fun login(email: String, password: String): Response<User>
    suspend fun register(user: User): Response<User>
    suspend fun update(user: User): Response<Void>
    suspend fun delete(id: Int): Response<Void>
    suspend fun getUser(userId: String): Response<User>

//    suspend fun googleRegister(user: User): Response<User>
//    suspend fun googleLogin(email: String, googleId: String): Response<User>
}