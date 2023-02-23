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
        val personaje = Personaje("Pepito", 200,"goblin","guerrero",200,200,200,200,mochila)
        val pepito = Personaje("Pepito2", 200,"goblin1","guerrero1",200,200,200,200,mochila)
        val personaje3 = Personaje("Pepito3", 200,"goblin2","guerrero2",200,200,200,200,mochila)
        //array de personajes

        setup()



        binding.button.setOnClickListener{
            db.collection("usuario_Luismi").document("ejemplo1").set(pepito)
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
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
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
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
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

    private fun showHome(email:String,provider: ProviderType){
        val homeIntent: Intent = Intent(this, prueba::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

}