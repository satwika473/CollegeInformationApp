package com.example.nec_info

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.nec_info.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sp: SharedPreferences
    lateinit var spe: SharedPreferences.Editor

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        sp = getSharedPreferences("sat", MODE_PRIVATE)

        // Check if the user is already logged in
        val isLoggedIn = sp.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            // Redirect to the home screen if the user is already logged in
            val homeIntent = Intent(this, Home::class.java)
            startActivity(homeIntent)
            finish() // Close the login activity
        }

        binding.sup.setOnClickListener {
            val i = Intent(this, register::class.java)
            startActivity(i)
        }

        binding.log.setOnClickListener(View.OnClickListener {
            if (binding.user.text.isEmpty() && binding.pwd.text.isEmpty()) {
                Toast.makeText(applicationContext, "Please enter data", Toast.LENGTH_SHORT).show()
            } else {
                val fromDbName = sp.getString("myname", "").toString()
                val fromDbPassword = sp.getString("pwd", "").toString()

                if (binding.user.text.toString() == fromDbName && binding.pwd.text.toString() == fromDbPassword) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                    // Save login status
                    spe = sp.edit()
                    spe.putBoolean("isLoggedIn", true) // Save login status as true
                    spe.apply()

                    // Redirect to the home screen
                    val homeIntent = Intent(this, Home::class.java)
                    startActivity(homeIntent)
                    finish() // Close the login activity
                } else {
                    Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
