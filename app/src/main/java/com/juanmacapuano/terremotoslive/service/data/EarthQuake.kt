package com.juanmacapuano.terremotoslive.service.data

data class EarthQuake(val id: String,
                    val place: String,
                    val magnitude: Double,
                    val time: Long,
                    val longitude: Double,
                    val latitude: Double
                    )
