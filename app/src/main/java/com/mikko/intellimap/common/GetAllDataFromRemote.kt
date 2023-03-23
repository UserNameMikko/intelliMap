package com.mikko.intellimap.common

import com.mikko.intellimap.retrofit.RetrofitClient
import com.mikko.intellimap.retrofit.RetrofitServices

object GetAllDataFromRemote {
    private const val BASE_URL = "https://escalastudio.ru/"
    val retrofitService: RetrofitServices
    get() = RetrofitClient.getData(BASE_URL).create(RetrofitServices::class.java)
}