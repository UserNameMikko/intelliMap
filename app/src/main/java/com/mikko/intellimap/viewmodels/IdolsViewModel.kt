package com.mikko.intellimap.viewmodels

import com.mikko.intellimap.R
import com.mikko.intellimap.common.GetAllDataFromRemote
import com.mikko.intellimap.data.IdolData
import com.mikko.intellimap.retrofit.RetrofitServices
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.postValue
import dev.icerock.moko.mvvm.livedata.readOnly
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class IdolsViewModel @Inject constructor() : ViewModel() {

    private val _photosFlow = MutableLiveData<Map<String, List<Int>>>(mapOf())
    val photosFlow = _photosFlow.readOnly()
    private var mService: RetrofitServices = GetAllDataFromRemote.retrofitService
    val liveData = MutableLiveData<IdolData>(IdolData())

    init {
        getAllIdols()
    }
    private fun getAllIdols() {
        MainScope().launch {
            mService.getData().enqueue(object : Callback<IdolData>{
                override fun onResponse(
                    call: Call<IdolData>,
                    response: Response<IdolData>
                ) {
                    println("RESPONSE FROM SERVER Idol VM: ${response.body()}")
                    liveData.postValue(response.body()!!)
                    println(response.code())
                }

                override fun onFailure(call: Call<IdolData>, t: Throwable) {
                    println("RESPONSE FROM SERVER:")
                    println(t.message)
                }
            })
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}