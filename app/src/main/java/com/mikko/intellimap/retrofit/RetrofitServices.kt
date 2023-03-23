package com.mikko.intellimap.retrofit

import com.mikko.intellimap.data.IdolData
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("json.php")
    fun getData(): Call<IdolData>
}