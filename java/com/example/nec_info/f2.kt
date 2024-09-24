package com.example.nec_info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class f2 : AppCompatActivity() {
    lateinit var next: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f2)
        next = findViewById(R.id.nxt)
        next.setOnClickListener {
            val i = Intent(this, f3::class.java)
            startActivity(i)
        }
    }
}