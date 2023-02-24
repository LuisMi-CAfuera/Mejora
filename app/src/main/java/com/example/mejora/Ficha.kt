
package com.example.mejora

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.mejora.databinding.ActivityFichaBinding
import com.google.gson.Gson

class Ficha : AppCompatActivity() {
    private lateinit var binding: ActivityFichaBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFichaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val shared= getSharedPreferences("Personaje", Context.MODE_PRIVATE)
        val gson = Gson()
        var json = shared.getString("Personaje", "")
        val p = gson.fromJson(json, Personaje::class.java)
        p.vida = 200
        p.pesoMochila = 200
        p.fuerza = (10..15).random()
        p.defensa = (1..5).random()
        binding.Seguir.isEnabled = false


        when(p.clase){

            "Guerrero" ->{
                binding.clase.text = "Clase: ${p.clase}"
                binding.Clase.setImageResource(R.drawable.guerrero2)
            }
            "Mago" ->{
                binding.clase.text = "Clase: ${p.clase}"
                binding.Clase.setImageResource(R.drawable.mago)
            }
            "Ladron" ->{
                binding.clase.text = "Clase: ${p.clase}"
                binding.Clase.setImageResource(R.drawable.ladron)
            }
            "Arquero" ->{
                binding.clase.text = "Clase: ${p.clase}"
                binding.Clase.setImageResource(R.drawable.arquero)
            }
        }



        when(p.raza){

            "Orco" ->{
                binding.raza.text = "Raza: ${p.raza}"
                binding.Raza.setImageResource(R.drawable.orco)

            }
            "Elfo" ->{
                binding.raza.text = "Raza: ${p.raza}"
                binding.Raza.setImageResource(R.drawable.elfo)

            }
            "Humano" ->{
                binding.raza.text = "Raza: ${p.raza}"
                binding.Raza.setImageResource(R.drawable.humano)

            }
            "Enano" ->{
                binding.raza.text = "Raza: ${p.raza}"
                binding.Raza.setImageResource(R.drawable.enano)

            }
        }
        binding.Nombre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.Seguir.isEnabled = true
                p.nombre = binding.Nombre.toString()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })


        binding.Fuerza.text = "Fuerza: ${p.fuerza}"
        binding.Defensa.text = "Defensa: ${p.defensa}"
        binding.Mochila.text= "Peso Mochila: ${p.pesoMochila}"
        binding.Vida.text = "Vida: ${p.vida}"
        binding.Monedero.text = "Monedero: 0$"


        binding.Volver.setOnClickListener{
            val intent = Intent(this@Ficha, ElegirClase::class.java)
            startActivity(intent)
        }

        binding.Seguir.setOnClickListener{
            val intent2 = Intent(this@Ficha, Dado::class.java)
            val editor = shared.edit()
            editor.clear()
            json = gson.toJson(p)
            editor.putString("Personaje", json)
            editor.apply()
            startActivity(intent2)
        }



    }
}