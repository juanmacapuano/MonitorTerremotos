package com.juanmacapuano.terremotoslive.service.data

class Geometry(private val coordinates: Array<Double>) {
    val latitude: Double
        get() = coordinates[0]

    val longitud: Double
        get() = coordinates[1]
}
