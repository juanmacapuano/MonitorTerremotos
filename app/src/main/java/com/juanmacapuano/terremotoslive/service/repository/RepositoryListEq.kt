package com.juanmacapuano.terremotoslive.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juanmacapuano.terremotoslive.service.data.EqResponse
import com.juanmacapuano.terremotoslive.service.data.Features
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryListEq {

    fun getListEq(): MutableLiveData<EqResponse> {
        val erResponse : MutableLiveData<EqResponse> = MutableLiveData<EqResponse>()
        apiEq.getListEq()
            .enqueue(object : Callback<EqResponse> {
                override fun onResponse(call: Call<EqResponse>, response: Response<EqResponse>) {
                    if (response.isSuccessful) {
                        erResponse.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<EqResponse>, t: Throwable) {
                    Log.d("retroffit", t.message.toString())
                }
            })
        return erResponse
    }

    fun getListEq2(): MutableList<EqResponse> {
        val erResponse : MutableList<EqResponse> = mutableListOf()
        apiEq.getListEq()
            .enqueue(object : Callback<EqResponse> {
                override fun onResponse(call: Call<EqResponse>, response: Response<EqResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            erResponse.add(data)
                        }
                    }
                }

                override fun onFailure(call: Call<EqResponse>, t: Throwable) {
                    Log.d("retroffit", t.message.toString())
                }
            })
        return erResponse
    }

    fun getAllEq() = apiEq.getListEq()
}