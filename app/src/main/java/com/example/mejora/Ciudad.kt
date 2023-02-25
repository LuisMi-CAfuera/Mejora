package com.example.mejora

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mejora.databinding.ActivityCiudadBinding
import com.google.gson.Gson

class Ciudad : AppCompatActivity() {
    private lateinit var binding: ActivityCiudadBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCiudadBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val shared= getSharedPreferences("Personaje", Context.MODE_PRIVATE)
        val gson = Gson()
        var json = shared.getString("Personaje", "")
        val p = gson.fromJson(json, Personaje::class.java)
        var cont = 0
        cont += intent.getIntExtra("cont", 0)
        var intent = Intent(this@Ciudad, Ciudad::class.java)
        binding.Entrar.text = "Avanzar"


        if(cont > 0){
            binding.Contador.text = "LLevas "+cont+"/5 batallas ganadas \n necesitas 5 para ganar"
        }


        binding.Entrar.setOnClickListener{
            val aleatorio = (1..3).random()
            val al2 = (1..10).random()
            var tipo = ""
            when(aleatorio){
                1 -> intent = Intent(this@Ciudad, ObjetoCiudad::class.java)
                2 -> intent = Intent(this@Ciudad, EnemigoCiudad::class.java)
                3 -> intent = Intent(this@Ciudad, MercaderCiudad::class.java)
            }

            if(al2 == 1){
                intent.putExtra("tipo", "Boss")
            }else if(al2 in 2..10){
                intent.putExtra("tipo", "Normal")
            }
            startActivity(intent)
        }


        binding.Continuar.isEnabled = cont == 5

        binding.Continuar.setOnClickListener{
            val intent = Intent(this@Ciudad, Dado::class.java)
            val editor = shared.edit()
            editor.clear()
            json = gson.toJson(p)
            editor.putString("Personaje", json)
            editor.apply()
            startActivity(intent)
        }
    }
}