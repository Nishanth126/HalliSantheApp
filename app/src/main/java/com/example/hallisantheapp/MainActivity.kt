package com.example.hallisanthedigital

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    private var filteredList = ArrayList<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val uploadBtn = findViewById<Button>(R.id.uploadBtn)
        val searchEt = findViewById<EditText>(R.id.searchEt)

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        filteredList.addAll(ProductStorage.productList)

        adapter = ProductAdapter(this, filteredList)
        recyclerView.adapter = adapter

        uploadBtn.setOnClickListener {
            startActivity(Intent(this, UploadProductActivity::class.java))
        }

        searchEt.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

                filteredList.clear()

                val searchText = s.toString().lowercase()

                for (item in ProductStorage.productList) {

                    if (item.name.lowercase().contains(searchText)) {
                        filteredList.add(item)
                    }
                }

                adapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onResume() {
        super.onResume()

        filteredList.clear()
        filteredList.addAll(ProductStorage.productList)

        adapter.notifyDataSetChanged()
    }
}