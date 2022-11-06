package com.example.walmartapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartapp.Product

class ItemListAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(private val binding: RowItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.titleTextView.text = product.title
            binding.colorTextView.text = "Color : " + product.color
            binding.priceTextView.text = "Price : $" + product.price

            Glide.with(binding.root.context).load(product.image).into(binding.imageView)

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, ProductDetailActivity::class.java)
                intent.putExtra("product", Gson().toJson(product))
                binding.root.context.startActivity(intent)
            }
        }
    }
}