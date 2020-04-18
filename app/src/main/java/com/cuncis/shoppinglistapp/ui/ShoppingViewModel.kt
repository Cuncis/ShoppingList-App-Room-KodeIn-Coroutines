package com.cuncis.shoppinglistapp.ui

import androidx.lifecycle.ViewModel
import com.cuncis.shoppinglistapp.data.db.entities.ShoppingItem
import com.cuncis.shoppinglistapp.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val respository: ShoppingRepository) : ViewModel() {

    fun insert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        respository.insert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        respository.delete(item)
    }

    fun getAllShoppingItems() = respository.getAllShoppingItems()

}