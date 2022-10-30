package com.example.walmartapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class ShoppingCategoryActivity : AppCompatActivity() {
    private lateinit var electronicsLayout: LinearLayout
    private lateinit var beautyLayout: LinearLayout
    private lateinit var clothingLayout: LinearLayout
    private lateinit var foodLayout: LinearLayout
    private lateinit var welcomeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_category)
        electronicsLayout = findViewById<LinearLayout>(R.id.electronicLayout)
        beautyLayout = findViewById<LinearLayout>(R.id.beautyLayout)
        clothingLayout = findViewById<LinearLayout>(R.id.clothingLayout)
        foodLayout = findViewById<LinearLayout>(R.id.foodLayout)
        welcomeText = findViewById<TextView>(R.id.tvWelcome)



        val ss: String = intent.getStringExtra("username").toString()
        welcomeText.text = "Welcome: $ss"

        beautyLayout.setOnClickListener {
            showToast("Beauty")
        }
        clothingLayout.setOnClickListener {
            showToast("Clothing")
        }
        electronicsLayout.setOnClickListener {
            showToast("Electronic")
        }
        foodLayout.setOnClickListener {
            showToast("Food")
        }

    }

    private fun showToast(msg: String) {
        Toast.makeText(
            this,
            "You have chosen the $msg category of shopping",
            Toast.LENGTH_SHORT
        )
            .show()
    }
}