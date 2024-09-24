package com.example.nec_info

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Button2Activity : AppCompatActivity() {
    lateinit var phbtn: Button
    private val phoneNumber="9885271324"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button2)
        phbtn=findViewById(R.id.phno)
        phbtn.setOnClickListener{
            val dialerIntent = Intent(Intent.ACTION_DIAL)
            dialerIntent.data = Uri.parse("tel:$phoneNumber")

            startActivity(dialerIntent)
        }
    }
}