package com.example.mejora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.mejora.databinding.ActivityMainBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setup()
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