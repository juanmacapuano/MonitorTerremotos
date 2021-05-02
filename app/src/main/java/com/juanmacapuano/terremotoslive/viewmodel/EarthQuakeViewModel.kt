package com.juanmacapuano.terremotoslive.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.juanmacapuano.terremotoslive.service.data.EarthQuake
import com.juanmacapuano.terremotoslive.service.data.EqResponse
import com.juanmacapuano.terremotoslive.service.data.Features
import com.juanmacapuano.terremotoslive.service.repository.RepositoryListEq
import com.juanmacapuano.terremotoslive.service.repository.ServiceAPIEq
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarthQuakeViewModel() : ViewModel() {

    private val repositoryListEq: RepositoryListEq = RepositoryListEq()

    private var _listEqResponseLiveData = MutableLiveData<MutableList<EarthQuake>>()
    val listEqResponseLiveData : LiveData<MutableList<EarthQuake>>
        get() = _listEqResponseLiveData


    init {
        viewModelScope.launch {
            getAllEq()
        }
    }

    private fun getAllEq()  {
        val eqResponse = repositoryListEq.getAllEq()
        eqResponse
            .enqueue(object : Callback<EqResponse> {
                override fun onResponse(call: Call<EqResponse>, response: Response<EqResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            _listEqResponseLiveData.value = parseEqResponse(data.features)
                        }
                    }
                }

                override fun onFailure(call: Call<EqResponse>, t: Throwable) {
                }
            })
    }

    private fun parseEqResponse(features : List<Features>): MutableList<EarthQuake> {
        val eqListResponse = mutableListOf<EarthQuake>()
        for (itemFeature in features) {
            val propertiesItem = itemFeature.properties
            val geometryItem = itemFeature.geometry
            val idItem = itemFeature.id

            val longitudeItem = geometryItem.longitud
            val latitudeItem = geometryItem.latitude

            val placeItem = propertiesItem.place
            val magnitudeItem = propertiesItem.mag
            val timeItem = propertiesItem.time

            eqListResponse.add(EarthQuake(idItem, placeItem, magnitudeItem, timeItem, longitudeItem, latitudeItem))
        }
        return eqListResponse
    }
}