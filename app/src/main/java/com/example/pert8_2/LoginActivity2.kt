package com.example.pert8_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONException
import org.json.JSONObject

class LoginActivity2 : AppCompatActivity() {
    private lateinit var email : EditText
    private lateinit var pass : EditText
    private lateinit var btnLog : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        //Inisialisasi
        email   = findViewById(R.id.EmailUser)
        pass    = findViewById(R.id.PasswordUser)
        btnLog  = findViewById(R.id.btnInput)

        btnLog.setOnClickListener {
            val checkEmail = email.text.toString().trim()
            val checkPass = pass.text.toString().trim()


            if(checkEmail.isEmpty()){
                email.error = "Email Empty"
            }else if(checkPass.isEmpty()){
                pass.error = " Password Empty"
            }else{
                val jsonObject = JSONObject()
                try {
                   jsonObject.put("email", email.text.toString().trim()) // yang di dalam "" harus sama dg yg di api
                    jsonObject.put("password", pass.text.toString().trim())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                AndroidNetworking.post("https://grocery-api.tonsu.site/auth/login")
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
                                    Toast.makeText(this@LoginActivity2, "Login Berhasil", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@LoginActivity2, MainActivity::class.java)
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
}