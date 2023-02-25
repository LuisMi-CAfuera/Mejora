package com.example.mejora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mejora.databinding.ActivityMuerteBinding

class Muerte : AppCompatActivity() {
    private lateinit var binding: ActivityMuerteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMuerteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.Volver.setOnClickListener {
            val intent = Intent(this@Muerte, Eleccion::class.java)
            startActivity(intent)
        }
    }
}