package com.gogood.mobile.menu.domain.models


data class  EnderecoResponse(
    val enderecos: Endereco,
    val tipoEndereco: String
)
data class Endereco(
    val id: Int,
    val cep: String,
    val rua: String,
    val numero: String,
    val cidade: String,
    val bairro: String,
    val createdAt: String,
)

enum class TipoEndereco(val tipo:String){
    CASA("Casa"),
    PARCEIRO_A("Parceiro(a)"),
    TRABALHO("Trabalho"),
    OUTRO("Outro"),
    FACULDADE("Faculdade")
}