package com.example.gogood.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gogood.data.network.ApiClient.userService
import com.example.gogood.data.network.LoginRequest
import com.example.gogood.domain.model.User
import com.example.gogood.domain.repository.IUserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: IUserRepository
) : ViewModel() {

    var isLoggedIn = false
        private set

    var user: User? = null
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = userService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                isLoggedIn = true
                user = response.body()
            }
        }
    }

    fun register(email: String, password: String) {
        TODO()
    }

    fun getUser(userId: String) {
        TODO()
    }

    fun updateUser(userId: String) {
        TODO()
    }

    fun deleteUser(userId: Int) {
        viewModelScope.launch {
            userRepository.delete(userId)
        }
    }
}