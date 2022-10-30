package com.example.walmartapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var fName: EditText
    private lateinit var lName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btnCreateAccount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        fName = findViewById(R.id.etFirstName)
        lName = findViewById(R.id.etLastName)
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        btnCreateAccount = findViewById(R.id.btnCreateAccount)

        btnCreateAccount.setOnClickListener {
            val fName = fName.editableText.toString().trim()
            val lName = lName.editableText.toString().trim()
            val userName = email.editableText.toString().trim()
            val password = password.editableText.toString().trim()

            if (fName.isEmpty() || lName.isEmpty() || userName.isEmpty() || password.isEmpty())
                Toast.makeText(this, "Fill All required Fields", Toast.LENGTH_SHORT).show()

            val returnData = Intent()
            returnData.putExtra("user", User(fName, lName, userName, password))
            setResult(RESULT_OK, returnData)
            finish()
        }
    }
}