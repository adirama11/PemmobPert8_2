package com.example.pert8_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.text.trimmedLength

class RegisterActivity : AppCompatActivity() {
    private lateinit var username : EditText
    private lateinit var email : EditText
    private lateinit var pass : EditText
    private lateinit var checkBox : CheckBox
    private lateinit var btnReg : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Inisialisasi
        username= findViewById(R.id.username)
        email   = findViewById(R.id.EmailUser)
        pass    = findViewById(R.id.PasswordUser)
        checkBox= findViewById(R.id.checkBox)
        btnReg  = findViewById(R.id.btnInput)

        btnReg.setOnClickListener {
            val checkUsername = username.text.toString().trim()
            val checkEmail = email.text.toString().trim()
            val checkPass = pass.text.toString().trim()
            val checkcekBox = checkBox.isChecked
            if (checkUsername.isEmpty()){

            }
        }
    }
}