package com.gogood.mobile.home.domain.models

data class BuscaEnderecoResponse(
    val candidates: List<Candidate>,
    val status: String
)

data class Candidate(
    val geometry: Geometry
)

data class Geometry(
    val location: Location,
    val viewport: Viewport
)

data class Location(
    val lat: Double,
    val lng: Double
)

data class Viewport(
    val northeast: Coordinates,
    val southwest: Coordinates
)

data class Coordinates(
    val lat: Double,
    val lng: Double
)
