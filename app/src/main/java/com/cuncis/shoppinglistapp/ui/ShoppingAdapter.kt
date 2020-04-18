package com.cuncis.shoppinglistapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cuncis.shoppinglistapp.R
import com.cuncis.shoppinglistapp.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.item_shopping.view.*

class ShoppingAdapter(var items: List<ShoppingItem>, private val viewModel: ShoppingViewModel)
    : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_shopping, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]

        holder.itemView.tvName.text = currentItem.name
        holder.itemView.tvAmount.text = currentItem.amount.toString()

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(currentItem)
        }

        holder.itemView.ivPlus.setOnClickListener {
            currentItem.amount++
            viewModel.insert(currentItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if (currentItem.amount > 0) {
                currentItem.amount--
                viewModel.insert(currentItem)
            }
        }
    }

    inner class ShoppingViewHolder(view: View): RecyclerView.ViewHolder(view)

}