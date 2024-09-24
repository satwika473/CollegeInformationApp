package com.example.nec_info

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.nec_info.databinding.ActivityRegisterBinding

class register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var sp: SharedPreferences
    lateinit var spe: SharedPreferences.Editor

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        sp = getSharedPreferences("sat", MODE_PRIVATE)

        binding.save.setOnClickListener(View.OnClickListener {
            if (isValidInput()) {
                val name = binding.user.text.toString()
                val pwd = binding.pwd.text.toString()
                spe = sp.edit()
                spe.putString("myname", name)
                spe.putString("pwd", pwd)
                spe.commit()
                spe.putBoolean("isLoggedIn", true)
                Toast.makeText(applicationContext, "Success: $name", Toast.LENGTH_SHORT).show()
                binding.reg.isEnabled = true
            }
        })

        binding.reg.setOnClickListener {
            val i = Intent(this, login::class.java)
            startActivity(i)
        }
    }

    private fun isValidInput(): Boolean {
        val name = binding.editTextUsername.text.toString()
        val rollno = binding.user.text.toString()
        val email = binding.email.text.toString()
        val pwd = binding.pwd.text.toString()
        val confirmPwd = binding.r1.text.toString()

        // Required fields validation
        if (name.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter a username", Toast.LENGTH_SHORT).show()
            return false
        }

        if (rollno.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter a roll number", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter an email", Toast.LENGTH_SHORT).show()
            return false
        }

        if (pwd.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter a password", Toast.LENGTH_SHORT).show()
            return false
        }

        if (confirmPwd.isEmpty()) {
            Toast.makeText(applicationContext, "Please confirm your password", Toast.LENGTH_SHORT).show()
            return false
        }

        // Roll number validation (starts with 22471A0, case-insensitive, 10 characters long)
        val rollnoRegex = Regex("^22471[Aa]0[0-9][A-Za-z0-9][0-9]\$")
        if (!rollno.matches(rollnoRegex)) {
            Toast.makeText(applicationContext, "Invalid roll number. It should start with 22471A0 and be 10 characters long.", Toast.LENGTH_SHORT).show()
            return false
        }

        // Email validation
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        if (!email.matches(emailRegex)) {
            Toast.makeText(applicationContext, "Invalid email format", Toast.LENGTH_SHORT).show()
            return false
        }

        // Password validation (at least 6 characters, contains a digit and a letter)
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d).{6,}\$")
        if (!pwd.matches(passwordRegex)) {
            Toast.makeText(applicationContext, "Password must be at least 6 characters long, and contain at least one letter and one number.", Toast.LENGTH_SHORT).show()
            return false
        }

        // Confirm password validation
        if (pwd != confirmPwd) {
            Toast.makeText(applicationContext, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
