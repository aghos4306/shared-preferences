package com.aghogho.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aghogho.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_main)
        //write data into shared preference
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        binding.btnSubmit.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val isAdult = binding.cbAdult.isChecked

            editor.apply {
                putString("name", name)
                putString("email", email)
                putBoolean("isAdult", isAdult)
                apply()
            }
        }

        //read data from shared preference
        binding.btnLoad.setOnClickListener {
            val name = sharedPref.getString("name", null)
            val email = sharedPref.getString("email", null)
            val isAdult = sharedPref.getBoolean("isAdult", false)

            binding.etName.setText(name)
            binding.etEmail.setText(email)
            binding.cbAdult.isChecked = isAdult
        }

    }
}