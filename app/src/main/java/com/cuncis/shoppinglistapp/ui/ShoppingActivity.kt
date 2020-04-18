package com.cuncis.shoppinglistapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuncis.shoppinglistapp.R
import com.cuncis.shoppinglistapp.data.db.ShoppingDatabase
import com.cuncis.shoppinglistapp.data.db.entities.ShoppingItem
import com.cuncis.shoppinglistapp.data.repositories.ShoppingRepository
import com.cuncis.shoppinglistapp.ui.add.AddDialogListener
import com.cuncis.shoppinglistapp.ui.add.AddShoppingItemDialog
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingAdapter(listOf(), viewModel)
        rv_shoppingList.layoutManager = LinearLayoutManager(this)
        rv_shoppingList.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun onButtonClicked(item: ShoppingItem) {
                    viewModel.insert(item)
                }
            }).show()
        }
    }
}
