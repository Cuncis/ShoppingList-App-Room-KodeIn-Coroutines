package com.cuncis.shoppinglistapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cuncis.shoppinglistapp.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_table")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>

}