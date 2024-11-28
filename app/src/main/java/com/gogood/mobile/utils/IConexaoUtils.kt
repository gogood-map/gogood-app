package com.gogood.mobile.utils

import kotlinx.coroutines.flow.Flow

interface IConexaoUtils {
    val observarConexao: Flow<Boolean>
}
