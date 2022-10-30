package com.example.walmartapp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private val usersList = ArrayList<User>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnCreateAccount = findViewById<Button>(R.id.btnCreateAccount)

        usersList.add(User("Anjana", "Sharma", "anjana@gmail.com", "123456"))
        usersList.add(User("Luzan", "Baral", "luzan@gmail.com", "123456"))
        usersList.add(User("Bijaya", "Parajuli", "bijaya@gmail.com", "123456"))
        usersList.add(User("Jaya", "Parajuli", "jaya@gmail.com", "123456"))

        tvForgotPassword.setOnClickListener {
            if (etEmail.text.toString().isEmpty()) {
                etEmail.error = "Email is required"
                Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user = usersList.find { it.userName == etEmail.text.toString().trim()}

            if(user === null ) {
                Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(user.userName))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Recovery of Password")
            intent.putExtra(Intent.EXTRA_TEXT, "Your password is ${user.password}.")
            intent.type = "message/rfc822"

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "No email app found", Toast.LENGTH_LONG).show()
            }
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

            val user = usersList.find {
                it.userName == etEmail.text.toString() && it.password == etPassword.text.toString()
            }
            if (user != null) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ShoppingCategoryActivity::class.java).apply { putExtra("userName", etEmail.text.toString()) })
                return@setOnClickListener
            }
            Toast.makeText(
                this,
                "Login Failed. Please check your email and password",
                Toast.LENGTH_SHORT
            ).show()
        }

        val resultLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: User = result.data?.extras?.get("user") as User
                usersList.add(data)
            }
        }

        btnCreateAccount.setOnClickListener {

            resultLauncher.launch(Intent(this, RegisterActivity::class.java))
        }
    }
}