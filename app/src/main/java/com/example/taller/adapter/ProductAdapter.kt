package com.example.taller.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taller.R
import androidx.recyclerview.widget.RecyclerView
import com.example.taller.model.Product
import kotlinx.android.synthetic.main.item_product.view.*


class ProductAdapter(val products: MutableList<Product>, val callback: (Product, Boolean) -> Unit): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View, val callback: (Product, Boolean) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product) {
            itemView.nameTextView.text = item.name
            itemView.descriptionTextView.text = item.description
            itemView.stockTextView.text = item.stock.toString()
            itemView.deleteButton.setOnClickListener {
                callback(item, true)
            }
            itemView.editButton.setOnClickListener {
                callback(item, false)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun deleteProduct(product: Product) {
        products.remove(product)
    }

    fun editProduct(product: Product, newName: String, newDescription: String, newquantity: Int) {
        val index = products.indexOf(product)
        products[index].name = newName
        products[index].description = newDescription
        products[index].stock = newquantity
    }
}