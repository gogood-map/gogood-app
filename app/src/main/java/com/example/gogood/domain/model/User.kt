package com.example.gogood.domain.model

data class User(
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val genero: String,
    val dataNascimento: String,
)