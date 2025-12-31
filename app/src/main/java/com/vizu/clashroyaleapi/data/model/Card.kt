package com.vizu.clashroyaleapi.data.model

data class Card (
    val key: String,
    val name: String,
    val scKey: String? = null,
    val elixir: Int? = 0,
    val type: String? = null,
    val rarity: String? = null,
    val arena: Int? = 0,
    val description: String? = null,
    val id: Int? = 0,
    val evolvedSpellsScKey: String? = null,
    val isEvolved: Boolean? = false
)