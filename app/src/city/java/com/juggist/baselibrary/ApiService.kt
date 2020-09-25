package com.juggist.baselibrary

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("assets/bitcoin")
    suspend fun getName():PriceBean
}