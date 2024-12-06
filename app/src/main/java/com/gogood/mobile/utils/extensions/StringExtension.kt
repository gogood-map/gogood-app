package com.gogood.mobile.utils.extensions

fun String.sentenceCase(): String {

    val palavraSeparada = this.split(" ")
    var palavraSentenceCase =  ""

    if(palavraSeparada.isNotEmpty()){
        palavraSeparada.forEach { palavraSeparadaAtual ->
            palavraSentenceCase +=
                palavraSeparadaAtual[0].toString() + palavraSeparadaAtual.substring(1).lowercase()
            palavraSentenceCase+=" "
        }
    }

    return palavraSentenceCase

}