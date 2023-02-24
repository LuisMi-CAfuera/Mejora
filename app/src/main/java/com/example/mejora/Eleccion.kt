package com.example.mejora

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mejora.databinding.ActivityEleccionBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.gson.Gson

class Eleccion : AppCompatActivity() {
    private lateinit var binding: ActivityEleccionBinding
    private val db = FirebaseFirestore.getInstance()
    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEleccionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val shared =  getSharedPreferences("Personaje", Context.MODE_PRIVATE)
        var editor = shared.edit()
        val gson = Gson()
        var json = ""
        val email = intent.getStringExtra("email")
        var partidas2 = ArrayList<Personaje>()

        val docRef = db.collection("Prueba1").document(email.toString())

        docRef.get().addOnSuccessListener {
            if(it.exists()){
                val usuario = it.toObject(Partidas::class.java)
                if (usuario != null) {
                    partidas2 = usuario.partidas
                    for (i in partidas2){
                        when(i.id){
                            1 ->{
                                binding.Nombre.text = i.nombre
                                binding.Clase.text = i.clase
                                binding.Raza.text = i.raza
                                binding.imageView.setImageResource(R.drawable.humano)
                            }
                            2 ->{
                                binding.Nombre2.text = i.nombre
                                binding.Clase2.text = i.clase
                                binding.Raza2.text = i.raza
                                binding.imageView2.setImageResource(R.drawable.humano)
                            }
                            3 ->{
                                binding.Nombre3.text = i.nombre
                                binding.Clase3.text = i.clase
                                binding.Raza3.text = i.raza
                                binding.imageView3.setImageResource(R.drawable.humano)
                            }
                        }
                    }
                }
            }
        }






    }
}