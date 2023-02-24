package com.example.mejora

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mejora.databinding.ActivityElegirRazaBinding
import com.google.gson.Gson

class ElegirRaza : AppCompatActivity() {
    private lateinit var binding: ActivityElegirRazaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityElegirRazaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val shared= getSharedPreferences("Personaje", Context.MODE_PRIVATE)
        val gson = Gson()
        var json = shared.getString("Personaje", "")
        val p = gson.fromJson(json, Personaje::class.java)
        binding.Aceptar.isEnabled = false

        binding.Orco.setOnClickListener{
            binding.imageView.setImageResource(R.drawable.orco)
            binding.Aceptar.isEnabled = true
            p.raza = "Orco"
        }
        binding.Elfo.setOnClickListener{
            binding.imageView.setImageResource(R.drawable.elfo)
            binding.Aceptar.isEnabled = true
            p.raza = "Elfo"
        }
        binding.Humano.setOnClickListener{
            binding.imageView.setImageResource(R.drawable.humano)
            binding.Aceptar.isEnabled = true
            p.raza = "Humano"
        }
        binding.Enano.setOnClickListener{
            binding.imageView.setImageResource(R.drawable.enano)
            binding.Aceptar.isEnabled = true
            p.raza = "Enano"
        }

        binding.Aceptar.setOnClickListener{
            val intent = Intent(this@ElegirRaza, Ficha::class.java)
            val editor = shared.edit()
            editor.clear()
            json = gson.toJson(p)
            editor.putString("Personaje", json)
            editor.apply()
            startActivity(intent)
        }
    }
}