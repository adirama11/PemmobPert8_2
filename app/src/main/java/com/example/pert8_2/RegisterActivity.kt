package com.example.pert8_2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError

import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.DateFormat.MEDIUM


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


            if (checkUsername.isEmpty()){
                username.error = "Username empty"
            }else if(checkEmail.isEmpty()){
                email.error = "Email Empty"
            }else if(checkPass.isEmpty()){
                pass.error = " Password Empty"
            }else{
                val check = checkBox.isChecked
                var number = 0
                if(check){
                    number = 1
                }else {
                    number = 0
                }
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("name", username.text.toString().trim()) //" name " itu harus sama dengan api
                    jsonObject.put("email", email.text.toString().trim()) // yang di dalam "" harus sama dg yg di api
                    jsonObject.put("password", pass.text.toString().trim())
                    jsonObject.put("terms", number)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                AndroidNetworking.post("https://grocery-api.tonsu.site/auth/register")
                    .addJSONObjectBody(jsonObject) // posting json
                    .addHeaders("Content-Type", "application/json")
                    .setPriority(com.androidnetworking.common.Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject?) {
                            //cek response
                            Log.d("ini cek response", response.toString())
                            try {
                                if(response?.getString("succes").equals("true")){
                                    Toast.makeText(this@RegisterActivity, "Register Berhasil", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@RegisterActivity, LoginActivity2::class.java)
                                    startActivity(intent)
                                }
                                 }catch (e : JSONException){

                            }
                        }

                        override fun onError(error: ANError?) {
                            // handle error
                        }
                    })
            }
        }
    }

    private fun registerUser (){

    }
}