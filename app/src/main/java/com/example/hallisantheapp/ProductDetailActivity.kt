package com.example.hallisanthedigital

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_product_details)

        val image =
            findViewById<ImageView>(R.id.detailsImage)

        val name =
            findViewById<TextView>(R.id.detailsName)

        val price =
            findViewById<TextView>(R.id.detailsPrice)

        val button =
            findViewById<Button>(R.id.checkStockBtn)

        val productName =
            intent.getStringExtra("name")

        val productPrice =
            intent.getStringExtra("price")

        val productImage =
            intent.getStringExtra("image")

        name.text = productName
        price.text = "₹$productPrice"

        image.setImageURI(Uri.parse(productImage))

        button.setOnClickListener {

            Toast.makeText(
                this,
                "Seller will contact you soon",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}