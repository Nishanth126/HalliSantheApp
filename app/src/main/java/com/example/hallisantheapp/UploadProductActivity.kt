package com.example.hallisanthedigital

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UploadProductActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_upload_product)

        imageView = findViewById(R.id.imageView)

        val selectImageBtn =
            findViewById<Button>(R.id.selectImageBtn)

        val uploadProductBtn =
            findViewById<Button>(R.id.uploadProductBtn)

        val nameEt =
            findViewById<EditText>(R.id.nameEt)

        val priceEt =
            findViewById<EditText>(R.id.priceEt)

        selectImageBtn.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)

            intent.type = "image/*"

            startActivityForResult(intent, 100)
        }

        uploadProductBtn.setOnClickListener {

            val name = nameEt.text.toString()

            val price = priceEt.text.toString()

            if (name.isEmpty() ||
                price.isEmpty() ||
                imageUri == null
            ) {

                Toast.makeText(
                    this,
                    "Fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val product =
                ProductModel(
                    name,
                    price,
                    imageUri!!
                )

            ProductStorage.productList.add(product)

            Toast.makeText(
                this,
                "Product Added Successfully",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (requestCode == 100 &&
            resultCode == Activity.RESULT_OK
        ) {

            imageUri = data?.data

            imageView.setImageURI(imageUri)
        }
    }
}