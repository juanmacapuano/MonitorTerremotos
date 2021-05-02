package com.juanmacapuano.terremotoslive.service.repository

import com.juanmacapuano.terremotoslive.service.data.EqResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ServiceAPIEq {

    @GET("all_hour.geojson")
    fun getListEq() : Call<EqResponse>

    companion object {
        const val BASE_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/"
    }
}

fun create(): ServiceAPIEq {
    val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    return Retrofit.Builder()
        .baseUrl(ServiceAPIEq.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServiceAPIEq::class.java)

}

var apiEq : ServiceAPIEq = create()