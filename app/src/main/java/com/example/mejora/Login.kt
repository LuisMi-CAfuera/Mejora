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



        setup()


    }
    private fun setup() {
        title = "Login"
        val p = ArrayList<Personaje>()

        binding.registro.setOnClickListener {
            if (binding.loginedit.text.isNotEmpty() && binding.password.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        binding.loginedit.text.toString(),
                        binding.password.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            db.collection("Prueba1").document(binding.loginedit.text.toString()).set(Partidas(binding.loginedit.text.toString(),p))
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