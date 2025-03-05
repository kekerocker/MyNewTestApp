package com.dsoft.mynewtestapp.domain.repository

import com.dsoft.mynewtestapp.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllItems(query: String): Flow<List<Item>>

    suspend fun deleteItem(item: Item)

    suspend fun updateItemAmount(id: Int, count: Int)
}