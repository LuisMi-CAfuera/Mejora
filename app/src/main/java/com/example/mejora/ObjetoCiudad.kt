package com.example.mejora

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mejora.databinding.ActivityObjetoBinding

import com.google.gson.Gson

class ObjetoCiudad : AppCompatActivity() {
    private lateinit var binding: ActivityObjetoBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObjetoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val shared= getSharedPreferences("Personaje", Context.MODE_PRIVATE)
        val gson = Gson()
        var json = shared.getString("Personaje", "")
        val p = gson.fromJson(json, Personaje::class.java)

        //Un objeto objetos inicializado con el peso y el valor y la vida
        val objeto = Objetos("Objeto Random",(1..5).random(), (1..10).random(), (1..20).random())




        binding.Objeto.setImageResource(R.drawable.objeto)


        binding.TextoObjeto.setText("Peso Maximo de la mochila: "+p.pesoMochila+"kg\n" +
                                    "Peso del objeto: "+objeto.peso +"kg\n"+
                                    "Valor del objeto: "+objeto.valor+"€\n"+
                                    "Vida del objeto: "+objeto.vida+"\n"+
                                    "Si coges este objeto, tu mochila solo podra llevar:"+ (p.pesoMochila-objeto.peso)+"kg\n")





        binding.Volver.setOnClickListener{
            val intent = Intent(this@ObjetoCiudad, Ciudad::class.java)
            val edit = shared.edit()
            edit.clear()
            json = gson.toJson(p)
            edit.putString("Personaje", json)
            edit.apply()
            startActivity(intent)
        }


        binding.Recoger.setOnClickListener{
            val intent = Intent(this@ObjetoCiudad, Ciudad::class.java)
            p.pesoMochila -= objeto.peso
            p.mochila.add(objeto)
            val edit = shared.edit()
            edit.clear()
            json = gson.toJson(p)
            edit.putString("Personaje", json)
            edit.apply()
            startActivity(intent)
        }

    }
}