package com.gogood.mobile.utils

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface ILocalizacaoUtils {
    fun observerLocalizacao(): Flow<LatLng>
    var permissaoLocalizacao: MutableStateFlow<Boolean>
}