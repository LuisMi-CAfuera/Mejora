package com.example.mejora

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mejora.databinding.ActivityEleccionBinding
import com.google.firebase.firestore.FirebaseFirestore
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
            val email = intent.getStringExtra("email")
            var partidas2 = ArrayList<Personaje>()
            var idBorrar = 0
            binding.Cargar.isEnabled = false
            binding.noTienes.text = "Cargando..."
            binding.imageView.setImageResource(R.drawable.interrogacion)
            binding.imageView2.setImageResource(R.drawable.interrogacion)
            binding.imageView3.setImageResource(R.drawable.interrogacion)





            val docRef = db.collection("Final").document(email.toString())

            docRef.get().addOnSuccessListener {
                if(it.exists()){
                    val partidas = it.toObject(Partidas::class.java)
                    if (partidas != null) {
                        partidas2 = partidas.partidas
                        if(partidas2.size == 0){
                            binding.Cargar.text = "Crear"
                            binding.noTienes.text = "No tienes personajes"
                            binding.Pesonaje1.setOnClickListener{
                                if (email != null) {
                                    crear(email,1)
                                }
                            }
                            binding.Personaje2.setOnClickListener{
                                if (email != null) {
                                    crear(email,2)
                                }
                            }
                            binding.Personaje3.setOnClickListener{
                                if (email != null) {
                                    crear(email,3)
                                }
                            }

                    }else if (partidas2.size == 1){
                            binding.Cargar.text = "Cagar"
                            binding.noTienes.text = "Tienes 1 personaje"

                            binding.Nombre.text = partidas2[0].nombre
                            binding.Clase.text = partidas2[0].clase
                            binding.Raza.text = partidas2[0].raza
                            binding.imageView.setImageResource(R.drawable.tick)
                            binding.Pesonaje1.setOnClickListener{
                                cargar(partidas2,1)
                                idBorrar = 1
                                binding.Borrar.isEnabled = true
                            }
                            binding.Personaje2.setOnClickListener{
                                if (email != null) {
                                    crear(email,2)
                                }
                            }
                            binding.Personaje3.setOnClickListener{
                                if (email != null) {
                                    crear(email,3)
                                }
                            }

                        }else if (partidas2.size == 2){
                            binding.Cargar.text = "Cagar"
                            binding.noTienes.text = "Tienes 2 personajes"

                            binding.Nombre.text = partidas2[0].nombre
                            binding.Clase.text = partidas2[0].clase
                            binding.Raza.text = partidas2[0].raza
                            binding.imageView.setImageResource(R.drawable.tick)
                            binding.Pesonaje1.setOnClickListener{
                                cargar(partidas2,1)
                                idBorrar = 1
                                binding.Borrar.isEnabled = true
                            }
                            binding.Nombre2.text = partidas2[1].nombre
                            binding.Clase2.text = partidas2[1].clase
                            binding.Raza2.text = partidas2[1].raza
                            binding.imageView2.setImageResource(R.drawable.tick)
                            binding.Personaje2.setOnClickListener{
                                cargar(partidas2,2)
                                idBorrar = 2
                                binding.Borrar.isEnabled = true
                            }
                            binding.Personaje3.setOnClickListener{
                                if (email != null) {
                                    crear(email,3)
                                }
                            }

                    }else if (partidas2.size == 3){
                            binding.Cargar.text = "Cagar"
                            binding.noTienes.text = "Tienes 3 personajes"
                            binding.Nombre.text = partidas2[0].nombre
                            binding.Clase.text = partidas2[0].clase
                            binding.Raza.text = partidas2[0].raza
                            binding.imageView.setImageResource(R.drawable.tick)
                            binding.Pesonaje1.setOnClickListener{
                                cargar(partidas2,1)
                                idBorrar = 1
                                binding.Borrar.isEnabled = true
                            }
                            binding.Nombre2.text = partidas2[1].nombre
                            binding.Clase2.text = partidas2[1].clase
                            binding.Raza2.text = partidas2[1].raza
                            binding.imageView2.setImageResource(R.drawable.tick)
                            binding.Personaje2.setOnClickListener{
                                cargar(partidas2,2)
                                idBorrar = 2
                                binding.Borrar.isEnabled = true
                            }
                            binding.Nombre3.text = partidas2[2].nombre
                            binding.Clase3.text = partidas2[2].clase
                            binding.Raza3.text = partidas2[2].raza
                            binding.imageView3.setImageResource(R.drawable.tick)
                            binding.Personaje3.setOnClickListener{
                                cargar(partidas2,3)
                                idBorrar = 3
                                binding.Borrar.isEnabled = true
                            }

                        }

                        binding.Borrar.setOnClickListener{
                            partidas!!.partidas.removeAt(idBorrar-1)
                            db.collection("Final").document(email.toString()).set(partidas!!)
                            if(idBorrar == 1){
                                binding.Nombre.text = "Nombre"
                                binding.Clase.text = "Clase"
                                binding.Raza.text = "Raza"
                                binding.imageView.setImageResource(R.drawable.interrogacion)
                                binding.Pesonaje1.setOnClickListener{
                                    if (email != null) {
                                        crear(email,1)
                                    }
                                }
                            }else if(idBorrar == 2){
                                binding.Nombre2.text = "Nombre"
                                binding.Clase2.text = "Clase"
                                binding.Raza2.text = "Raza"
                                binding.imageView2.setImageResource(R.drawable.interrogacion)
                                binding.Personaje2.setOnClickListener{
                                    if (email != null) {
                                        crear(email,2)
                                    }
                                }
                            }else if(idBorrar == 3) {
                                binding.Nombre3.text = "Nombre"
                                binding.Clase3.text = "Clase"
                                binding.Raza3.text = "Raza"
                                binding.imageView3.setImageResource(R.drawable.interrogacion)
                                binding.Personaje3.setOnClickListener {
                                    if (email != null) {
                                        crear(email, 3)
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        fun cargar(partidas : ArrayList<Personaje> , id: Int){
            val shared =  getSharedPreferences("Personaje", Context.MODE_PRIVATE)
            binding.Cargar.isEnabled = true
            val editor = shared.edit()
            val gson = Gson()
            var json = ""
            var personaje = Personaje()
            for(i in partidas){
                if(i.id == id){
                    personaje = i
                }
            }
            binding.Cargar.setOnClickListener{
                json = gson.toJson(personaje)
                editor.putString("Personaje", json)
                editor.apply()
                val intent = Intent(this@Eleccion, Dado::class.java)
                startActivity(intent)
            }
        }
        @SuppressLint("SetTextI18n")
        fun crear(email : String, id : Int){
            val shared =  getSharedPreferences("Personaje", Context.MODE_PRIVATE)
            val editor = shared.edit()
            val gson = Gson()
            var json = ""
            val personaje = Personaje()
            personaje.email = email
            personaje.id = id

            binding.Cargar.isEnabled = true
            binding.Cargar.text = "Crear"
            binding.Cargar.setOnClickListener{
                json = gson.toJson(personaje)
                editor.putString("Personaje", json)
                editor.apply()
                val intent = Intent(this@Eleccion, ElegirClase::class.java)
                intent.putExtra("email", email)
                intent.putExtra("id", id)
                startActivity(intent)
            }

        }




    }

