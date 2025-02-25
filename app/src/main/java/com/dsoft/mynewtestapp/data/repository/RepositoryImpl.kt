package com.dsoft.mynewtestapp.data.repository

import com.dsoft.mynewtestapp.data.db.ItemDao
import com.dsoft.mynewtestapp.data.mapper.itemDBOtoDomain
import com.dsoft.mynewtestapp.data.mapper.itemToDBO
import com.dsoft.mynewtestapp.data.model.ItemDBO
import com.dsoft.mynewtestapp.data.util.PrefsWrapper
import com.dsoft.mynewtestapp.domain.model.Item
import com.dsoft.mynewtestapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val dao: ItemDao,
    private val prefsWrapper: PrefsWrapper
) : Repository {
    override suspend fun insertItems() {
        val needToPopulate = !prefsWrapper.isInitiated()
        if (needToPopulate) {
            dao.insertAll(ItemDBO.list)
            prefsWrapper.setInitiated()
        }
    }

    override fun getAllItems(query: String): Flow<List<Item>> {
        return dao.getItems(query).map { it.map(itemDBOtoDomain) }
    }

    override suspend fun deleteItem(item: Item) {
        dao.deleteItem(item.let(itemToDBO))
    }

    override suspend fun insertItem(item: Item) {
        dao.insertItem(item.let(itemToDBO))
    }
}