package com.example.gogood.data.repository

import com.example.gogood.data.network.UserService
import com.example.gogood.domain.model.User
import com.example.gogood.domain.repository.IUserRepository
import retrofit2.Response

class UserRepository(
    private val userService: UserService
): IUserRepository {
    override suspend fun delete(id: Int): Response<Void> {
        return userService.delete(id)
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