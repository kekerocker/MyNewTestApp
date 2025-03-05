package com.dsoft.mynewtestapp.data.repository

import com.dsoft.mynewtestapp.data.db.ItemDao
import com.dsoft.mynewtestapp.data.mapper.itemDBOtoDomain
import com.dsoft.mynewtestapp.data.mapper.itemToDBO
import com.dsoft.mynewtestapp.domain.model.Item
import com.dsoft.mynewtestapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val dao: ItemDao
) : Repository {
    override fun getAllItems(): Flow<List<Item>> {
        return dao.getItems().map { it.map(itemDBOtoDomain) }
    }

    override suspend fun deleteItem(item: Item) {
        dao.deleteItem(item.let(itemToDBO))
    }

    override suspend fun updateItemAmount(id: Int, count: Int) {
        dao.updateAmount(id, count)
    }
}