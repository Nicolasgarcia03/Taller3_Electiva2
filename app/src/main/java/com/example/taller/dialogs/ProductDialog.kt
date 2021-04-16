package com.example.taller.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.taller.R
import kotlinx.android.synthetic.main.dialog_product.*
import java.lang.Integer.parseInt

class ProductDialog(context: Context, val name: String, val description: String, val quantity: Int, private val callback: (String, String, Int) -> Unit) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_product)
        nameEditText.setText(name)
        descriptionEditText.setText(description)
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val quantity: Int = parseInt(quantityEditText.text.toString())
            callback(name, description, quantity)
            dismiss()
        }
    }
}