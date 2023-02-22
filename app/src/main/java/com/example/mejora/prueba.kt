package com.example.mejora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mejora.databinding.ActivityPruebaBinding
import com.google.firebase.auth.FirebaseAuth

enum  class ProviderType{
    BASIC
}
class prueba : AppCompatActivity() {
    private lateinit var binding : ActivityPruebaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPruebaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    private fun setup(email:String , provider: String){

        title = "Inicio"
        binding.emailTextView.text = email
        binding.providerTextView.text = provider

        binding.cerrarSesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}