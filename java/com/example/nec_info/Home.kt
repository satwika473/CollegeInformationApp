package com.example.nec_info

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nec_info.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var binding: ActivityMainBinding
    private lateinit var bv: BottomNavigationView
    lateinit var sp: SharedPreferences
    lateinit var spe: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_home)

        bv=findViewById(R.id.bv)// Use binding to initialize the BottomNavigationView
        sp = getSharedPreferences("sat", MODE_PRIVATE)
        // Set the initial fragment
        replaceFragment(homef())

        // Set the BottomNavigationView listener
        bv.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> replaceFragment(homef())
                R.id.location -> replaceFragment(location())
                R.id.contact -> replaceFragment(contact())
                R.id.profile -> replaceFragment(profile())
                R.id.feed->openGallery()
                else -> {}
            }
            true
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            selectedImageUri?.let {
                imageView.setImageURI(it)
                // You can now use the URI to upload the image to your app's server or any storage.
            }
        }
    }

    // Method to replace fragments
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    // Inflate the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Handle menu item selections
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settingId -> {
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)
            }
            R.id.aboutId -> {
                val intent = Intent(this, About::class.java)
                startActivity(intent)
            }
            R.id.logoutId -> {
                spe = sp.edit()
                spe.putBoolean("isLoggedIn", false) // Save login status as true
                spe.apply()
                val intent = Intent(this, login::class.java)
                startActivity(intent)

            }
        }
        return super.onOptionsItemSelected(item)
    }

}

