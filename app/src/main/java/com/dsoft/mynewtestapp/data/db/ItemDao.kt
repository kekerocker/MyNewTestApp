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
    @Query("UPDATE item SET amount = :count WHERE id = :id")
    suspend fun updateAmount(id: Int, count: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ItemDBO>)

    @Query("SELECT * FROM item WHERE (:query = '' OR name LIKE '%' || :query || '%')")
    fun getItems(query: String): Flow<List<ItemDBO>>

    @Delete
    suspend fun deleteItem(item: ItemDBO)

    @Query("DELETE FROM item WHERE id = :id")
    suspend fun deleteItemById(id: Int)
}