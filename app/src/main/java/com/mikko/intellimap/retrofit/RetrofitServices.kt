package com.mikko.intellimap.retrofit

import com.mikko.intellimap.data.IdolData
import com.mikko.intellimap.data.IdolDataItem
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("idols.php")
    fun getData(): Call<MutableList<IdolDataItem>>
}