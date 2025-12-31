package com.vizu.clashroyaleapi.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ClashRoyaleNetwork {
    private const val BASE_URL = "https://raw.githubusercontent.com/RoyaleAPI/cr-api-data/refs/heads/master/docs/json/"

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val api: ClashRoyaleApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ClashRoyaleApi::class.java)
}