package com.example.gogood.data.repository

import com.example.gogood.data.network.UserService
import com.example.gogood.domain.model.User
import com.example.gogood.domain.repository.UserRepository
import retrofit2.Response

class UserRepositoryImpl(
    private val userService: UserService

): UserRepository {
    override suspend fun delete(email: String): Response<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(userId: String): Response<User> {
        TODO("Not yet implemented")
    }

    override suspend fun register(user: User): Response<User> {
        TODO("Not yet implemented")
    }

    override suspend fun update(user: User): Response<Void> {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): Response<User> {
        TODO("Not yet implemented")
    }
}