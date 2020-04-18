package com.cuncis.shoppinglistapp.ui.add

import com.cuncis.shoppinglistapp.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onButtonClicked(item: ShoppingItem)
}