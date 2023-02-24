package com.example.mejora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.mejora.databinding.ActivityLoginBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val mochila = ArrayList<Objetos>()
        mochila.add(Objetos("Espada", 1, 10, 10))
        mochila.add(Objetos("Escudo", 1, 10, 10))
        val personaje = Personaje(1,"Pepito", 200,"goblin","guerrero",200,200,200,200,mochila)
        val personaje2 = Personaje(2,"Luis", 200,"goblin1","guerrero1",200,200,200,200,mochila)
        val personaje3 = Personaje(3,"Juan", 200,"goblin2","guerrero2",200,200,200,200,mochila)

        val personajes = ArrayList<Personaje>()
        personajes.add(personaje)
        personajes.add(personaje2)
        personajes.add(personaje3)

        val usuario = Partidas("pueba@gmail.com",personajes)

        setup()

        for (i in usuario.partidas){
            println(i.nombre)
        }



        binding.button.setOnClickListener{
            db.collection("Prueba1").document(usuario.email).set(usuario)
        }

        binding.button2.setOnClickListener{
            val intent = Intent(this, Eleccion::class.java)
            intent.putExtra("email", usuario.email)
            startActivity(intent)
        }
    }
    private fun setup() {
        title = "Login"

        binding.registro.setOnClickListener {
            if (binding.loginedit.text.isNotEmpty() && binding.password.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        binding.loginedit.text.toString(),
                        binding.password.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            pasar(binding.loginedit.text.toString())
                        }else{
                            showAlert()
                        }
                    }
            }
        }
        binding.loginbutton.setOnClickListener {
            if (binding.loginedit.text.isNotEmpty() && binding.password.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        binding.loginedit.text.toString(),
                        binding.password.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            pasar(binding.loginedit.text.toString())
                        }else{
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun pasar(email : String){
        val intent = Intent(this, Eleccion::class.java)
        intent.putExtra("email", email)
        startActivity(intent)
    }


}