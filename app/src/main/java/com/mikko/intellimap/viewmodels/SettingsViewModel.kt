package com.mikko.intellimap.viewmodels

import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikko.intellimap.common.GetAllDataFromRemote
import com.mikko.intellimap.data.IdolData
import com.mikko.intellimap.data.IdolDataItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.postValue
import dev.icerock.moko.mvvm.livedata.readOnly
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(): ViewModel(){
    private val _images = MutableLiveData<List<String>>(mutableListOf())
    private val _liveData = MutableLiveData<MutableList<IdolDataItem>>(mutableListOf())

    val images = _images.readOnly()
    val liveData = _liveData.readOnly()
    private val mService = GetAllDataFromRemote.retrofitService
    /**
     * + Получили список идолов
     * - получили из фрагмента данные об отсканированном идоле (slug)
     * - нашли url с его фотками
     * - загрузили фотки в хранилище
     *
     * */


    init {
        if (_liveData.value.isEmpty())
            getAllIdols()

    }
    fun downloadImage(idol: String) {
        val directory = File(Environment.DIRECTORY_PICTURES)

        if (!directory.exists()) {
            directory.mkdirs()
        }
        Environment.getExternalStorageDirectory().absolutePath +
                "/idols/images/$idol"
    }
    private fun getAllIdols() {
        viewModelScope.launch {
            mService.getData().enqueue(object : Callback<MutableList<IdolDataItem>> {
                override fun onResponse(
                    call: Call<MutableList<IdolDataItem>>,
                    response: Response<MutableList<IdolDataItem>>
                ) {
                    println("RESPONSE FROM SERVER Settings: ${response.body()}")
                    if (response.body() != null) {
                        _liveData.postValue(response.body()!!)
                    }
                    println(response.code())
                }

                override fun onFailure(call: Call<MutableList<IdolDataItem>>, t: Throwable) {
                    println("RESPONSE FROM SERVER Settings:")
                    println(t.message)
                }
            })
        }
    }



   fun getImagesFromRemote(idol: String): List<String>{
       var images = listOf<String>()
       return if (_liveData.value.isNotEmpty()) {
           val idol = _liveData.value.filter {
               it.slug == idol
           }
           println("KUMIR SIZE: "+idol.size)
           println("items: $idol")

           /*val images = idol.first().images
           println(images)
           images = _liveData.value.photos.filter {
               it.contains(idol)
           }
           println(images)*/
           images
       } else {
           getAllIdols()
           images
       }

    }

}