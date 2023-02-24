package com.example.mejora

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mejora.databinding.ActivityElegirClaseBinding
import com.google.gson.Gson

class ElegirClase : AppCompatActivity() {
    private lateinit var binding: ActivityElegirClaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityElegirClaseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val p = Personaje()
        binding.Aceptar.isEnabled = false


        binding.Guerrero.setOnClickListener {
            binding.imagenes.setImageResource(R.drawable.guerrero2)
            binding.Aceptar.isEnabled = true
            p.clase = "Guerrero"
        }

        binding.Mago.setOnClickListener {
            binding.imagenes.setImageResource(R.drawable.mago)
            binding.Aceptar.isEnabled = true
            p.clase= "Mago"

        }

        binding.ladron.setOnClickListener {
            binding.imagenes.setImageResource(R.drawable.ladron)
            binding.Aceptar.isEnabled = true
            p.clase = "Ladron"

        }

        binding.Arquero.setOnClickListener {
            binding.imagenes.setImageResource(R.drawable.arquero)
            binding.Aceptar.isEnabled = true
            p.clase = "Arquero"

        }


        binding.Aceptar.setOnClickListener {
            //cambiar de activity
            val intent = Intent(this@ElegirClase, ElegirRaza::class.java)
            val shared =  getSharedPreferences("Personaje", Context.MODE_PRIVATE)
            val editor = shared.edit()
            editor.clear()
            val gson = Gson()
            val json = gson.toJson(p)
            editor.putString("Personaje", json)
            editor.apply()
            startActivity(intent)
        }
    }
}