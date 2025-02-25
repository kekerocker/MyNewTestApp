package com.dsoft.mynewtestapp.domain.model

import java.time.LocalDateTime

data class Item(
    val id: Int,
    val name: String,
    val date: LocalDateTime,
    val tags: List<String>,
    val amount: Int
)