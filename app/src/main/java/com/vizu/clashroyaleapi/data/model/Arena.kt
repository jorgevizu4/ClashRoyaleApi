package com.vizu.clashroyaleapi.data.model

data class Arena (
    val id: Int,
    val name: String,
    val key: String? = null,
    val title: String? = null,
    val subtitle: String? = null
)