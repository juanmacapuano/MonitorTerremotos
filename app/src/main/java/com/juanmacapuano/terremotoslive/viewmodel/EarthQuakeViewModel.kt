package com.juanmacapuano.terremotoslive.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.juanmacapuano.terremotoslive.service.api.StatusResponseAPI
import com.juanmacapuano.terremotoslive.service.database.getDatabase
import com.juanmacapuano.terremotoslive.service.repository.RepositoryListEq
import kotlinx.coroutines.launch
import java.net.UnknownHostException

private val TAG = EarthQuakeViewModel::class.java.simpleName

class EarthQuakeViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repositoryListEq: RepositoryListEq = RepositoryListEq(database)

    private val _statusLoading = MutableLiveData<StatusResponseAPI>()
    val statusLoading: LiveData<StatusResponseAPI>
        get() = _statusLoading

    val earthQuakeList = repositoryListEq.earthQuakeList

    init {
        viewModelScope.launch {
            try {
                _statusLoading.value = StatusResponseAPI.LOADING
                repositoryListEq.getAllEarthQuake()
                _statusLoading.value = StatusResponseAPI.DONE
            } catch (e: UnknownHostException) {
                _statusLoading.value = StatusResponseAPI.ERROR
                Log.d(TAG, "No internet connection", e)
            }
        }
    }
}