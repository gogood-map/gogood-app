package com.gogood.mobile.auth.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioResponse(
    var userId: Int? = null,
    var nome: String? = null,
    var email: String? = null,
    var token: String?= null,
    var genero: String?= null,
    val dt_nascimento: String?= null
)


