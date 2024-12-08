package com.gogood.mobile.address.domain.model

data class AddressRequest(
    var cep: String = "",
    var rua: String = "",
    var cidade: String = "",
    var numero: String = "",
    var bairro: String = "",
    var tipoEndereco: String = "",
    var usuarioId: Int = 0
)