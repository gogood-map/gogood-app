package com.gogood.mobile.auth.domain.models

data class UsuarioCadastroRequest(
    var nome: String = "",
    var email: String ="",
    var senha: String = "",
    var genero: String = "",
    var dt_Nascimento: String = "",
)