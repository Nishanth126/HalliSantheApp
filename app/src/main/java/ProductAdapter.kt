package com.example.hallisanthedigital

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val context: Context,
    private val list: ArrayList<ProductModel>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.productImage)
        val name: TextView = itemView.findViewById(R.id.productName)
        val price: TextView = itemView.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val model = list[position]

        holder.name.text = model.name
        holder.price.text = "₹${model.price}"

        holder.image.setImageURI(model.imageUri)

        holder.itemView.setOnClickListener {

            val intent =
                Intent(context, ProductDetailsActivity::class.java)

            intent.putExtra("name", model.name)
            intent.putExtra("price", model.price)
            intent.putExtra("image", model.imageUri.toString())

            context.startActivity(intent)
        }
    }
}
