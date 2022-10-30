package com.example.walmartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnCreateAccount = findViewById<Button>(R.id.btnCreateAccount)

        tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Forgot Password Clicked", Toast.LENGTH_SHORT).show()
        }

        btnSignIn.setOnClickListener {
            if (etEmail.text.toString().isEmpty()) {
                etEmail.error = "Email is required"
                return@setOnClickListener
            }

            if (etPassword.text.toString().isEmpty()) {
                etPassword.error = "Password is required"
                return@setOnClickListener
            }

            Toast.makeText(
                this,
                "Sign In: email: ${etEmail.text} password: ${etPassword.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnCreateAccount.setOnClickListener {
            Toast.makeText(this, "Create Account Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}