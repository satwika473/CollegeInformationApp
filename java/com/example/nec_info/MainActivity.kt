package com.example.nec_info

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var logo: ImageView
    lateinit var sp: SharedPreferences
    lateinit var spe: SharedPreferences.Editor

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
       val splashTimeOut: Long = 1000
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            // Check if the user is already logged in
            val sp = getSharedPreferences("sat", MODE_PRIVATE)
            val isLoggedIn = sp.getBoolean("isLoggedIn", false)

            if (isLoggedIn) {
                // Redirect to the home screen if the user is already logged in
                val homeIntent = Intent(this, Home::class.java)
                startActivity(homeIntent)
            } else {
                // Redirect to the MainActivity if the user is not logged in
                val mainIntent = Intent(this, fg1::class.java)
                startActivity(mainIntent)
            }
            finish() // Close the SplashActivity
        }, splashTimeOut)
    }
}

