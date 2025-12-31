package com.vizu.clashroyaleapi.data.remote

import com.vizu.clashroyaleapi.data.model.Arena
import com.vizu.clashroyaleapi.data.model.Card
import retrofit2.http.GET
import retrofit2.http.Query

interface ClashRoyaleApi {
    @GET("cards.json")
    suspend fun getCards(): List<Card>
    @GET("cards_evo.json")
    suspend fun getCardsEvo(): List<Card>
    @GET("arenas.json")
    suspend fun getArenas(): List<Arena>
}