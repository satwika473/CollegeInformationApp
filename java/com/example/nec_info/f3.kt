package com.example.nec_info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class f3 : AppCompatActivity() {
    lateinit var next: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f3)
        next = findViewById(R.id.nxt)
        next.setOnClickListener {
            val i = Intent(this, login::class.java)
            startActivity(i)
        }
    }
}