package com.gogood.mobile.utils.extensions

fun String.sentenceCase(): String {
    var palavraSeparada = this.split(" ")
    var palavraSentenceCase =  ""
    palavraSeparada.forEach { palavraSeparadaAtual ->
        palavraSentenceCase +=
            palavraSeparadaAtual[0].toString() + palavraSeparadaAtual.substring(1).lowercase()
        palavraSentenceCase+=" "

    }
    return palavraSentenceCase

}