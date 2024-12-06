package com.gogood.mobile.home.domain.models

data class RotaHistoricoRequest(
    val id_usuario: Int,
    val origem: String,
    val destino: String,
    val meio_locomocao: String
)