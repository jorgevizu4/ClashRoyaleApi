package com.vizu.clashroyaleapi.data.repository

import com.vizu.clashroyaleapi.data.model.Arena
import com.vizu.clashroyaleapi.data.model.Card
import com.vizu.clashroyaleapi.data.remote.ClashRoyaleApi

class ClashRoyaleRepository(
    private val api: ClashRoyaleApi
) {
    suspend fun getCards(): List<Card> {
        return api.getCards()
    }
    suspend fun getArenas(): List<Arena> {
        return api.getArenas()
    }
}