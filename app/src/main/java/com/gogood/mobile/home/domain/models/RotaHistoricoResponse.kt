package com.gogood.mobile.home.domain.models

data class RotaHistoricoResponse(
    val id: Int,
    val origem: String,
    val destino: String,
    val meio_locomocao: String,
    val idUsuario: Int,
    val created_at: String
)
