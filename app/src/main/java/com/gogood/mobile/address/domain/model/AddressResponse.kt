package com.gogood.mobile.address.domain.model

data class AddressResponse(
    var enderecos: List<Endereco> = emptyList(),
    var tipoEndereco: String = ""
)

data class Endereco(
    var id: Int = 0,
    var cep: String = "",
    var rua: String = "",
    var numero: String = "",
    var cidade: String = "",
    var bairro: String = "",
    var createdAt: String = "",
    var idUsuario: Int = 0
)
