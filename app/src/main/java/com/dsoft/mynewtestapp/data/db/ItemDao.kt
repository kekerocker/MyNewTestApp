package com.dsoft.mynewtestapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsoft.mynewtestapp.data.model.ItemDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ItemDBO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ItemDBO>)

    @Query("SELECT * FROM item WHERE (:query = '' OR name LIKE '%' || :query || '%')")
    fun getItems(query: String): Flow<List<ItemDBO>>

    @Delete
    fun deleteItem(item: ItemDBO)
}