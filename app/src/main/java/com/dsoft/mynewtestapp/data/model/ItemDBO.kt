package com.dsoft.mynewtestapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
class ItemDBO(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int
)