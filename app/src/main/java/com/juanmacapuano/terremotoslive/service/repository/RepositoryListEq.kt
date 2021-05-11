package com.juanmacapuano.terremotoslive.service.repository

import androidx.lifecycle.LiveData
import com.juanmacapuano.terremotoslive.service.api.apiEq
import com.juanmacapuano.terremotoslive.service.data.EarthQuake
import com.juanmacapuano.terremotoslive.service.data.EqResponse
import com.juanmacapuano.terremotoslive.service.data.Features
import com.juanmacapuano.terremotoslive.service.database.EqDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val TAG = RepositoryListEq::class.java.simpleName

class RepositoryListEq(private val database: EqDatabase) {

    val earthQuakeList: LiveData<MutableList<EarthQuake>> = database.eqDao.getAllEarthQuake()

    suspend fun getAllEarthQuake() {
        return withContext(Dispatchers.IO) {
            val eqResponse = apiEq.getListEq()
            val listResponse: MutableList<EarthQuake> = parseEqResponse(eqResponse)
            //insert to Room
            database.eqDao.insertAll(listResponse)
        }
    }

    private fun parseEqResponse(eqResponse: EqResponse): MutableList<EarthQuake> {
        val eqListResponse = mutableListOf<EarthQuake>()
        var features: List<Features> = mutableListOf()
        features = eqResponse.features
        for (itemFeature in features) {
            val propertiesItem = itemFeature.properties
            val geometryItem = itemFeature.geometry
            val idItem = itemFeature.id

            val longitudeItem = geometryItem.longitud
            val latitudeItem = geometryItem.latitude

            val placeItem = propertiesItem.place
            val magnitudeItem = propertiesItem.mag
            val timeItem = propertiesItem.time

            eqListResponse.add(
                EarthQuake(
                    idItem,
                    placeItem,
                    magnitudeItem,
                    timeItem,
                    longitudeItem,
                    latitudeItem
                )
            )
        }
        return eqListResponse
    }
}