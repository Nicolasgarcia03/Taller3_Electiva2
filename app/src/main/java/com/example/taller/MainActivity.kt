package com.example.taller

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taller.model.Product
import com.example.taller.adapter.ProductAdapter
import com.example.taller.dialogs.ProductDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtons()
        setupList()
    }

    private fun setupButtons() {
        addButton.setOnClickListener {
            val dialog = ProductDialog(this, "", "", 1) { name, description, quantity ->
                addProduct(name, description, quantity)
            }
            dialog.show()
        }
    }

    private fun setupList() {
        val products = mutableListOf(Product("Wiskey Something", "Something special 1973", 10),
            Product("Wiskey Buchanans ", "Buchanans special reserve 18 años", 7))

        adapter = ProductAdapter(products) { item, isDelete ->
            if(isDelete) deleteProduct(item)
            else editProduct(item)
        }
        productRecyclerView.adapter = adapter
        productRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun addProduct(name: String, description: String, quantity: Int) {
        val product = Product(name, description, quantity)
        adapter.addProduct(product)
        adapter.notifyDataSetChanged()
    }

    private fun deleteProduct(product: Product) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar registro")
        builder.setMessage("¿Esta seguro que desea eliminar el registro?")
        builder.setPositiveButton("Aceptar") { _, _ ->
            adapter.deleteProduct(product)
            adapter.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancelar") { _,_ ->
            Toast.makeText(this, "No se ha eliminado el registro", Toast.LENGTH_LONG).show()
        }
        builder.setCancelable(false)
        builder.show()

    }

    private fun updateProduct(product: Product, name: String, description: String, quantity: Int) {
        adapter.editProduct(product, name, description, quantity)
        adapter.notifyDataSetChanged()
    }

    private fun editProduct(product: Product) {
        val dialog = ProductDialog(this, product.name, product.description, product.stock) { name, description, quantity ->
            updateProduct(product, name, description, quantity)
        }
        dialog.show()
    }
}