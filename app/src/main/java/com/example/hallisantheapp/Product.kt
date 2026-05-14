package com.example.hallisanthedigital

import android.net.Uri

object ProductStorage {
    val productList = ArrayList<ProductModel>()
}

data class ProductModel(
    var name: String,
    var price: String,
    var imageUri: Uri
)
