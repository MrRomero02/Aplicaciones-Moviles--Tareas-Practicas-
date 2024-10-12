package com.example.proyecto4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import com.example.proyecto4.databinding.ActivitySecondBinding



class SecondActivity : AppCompatActivity() {
    //1. Declaramos la variable binding

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //2. Inicializarr el binding
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //3. Obtenemos el valor de username
        val username = intent.getStringExtra("username")

        //4. Mostramos el mensaje de bienvenido

        binding.tvWelcome.text = "Bienvenido $username"

        // 5. Configuramos el boton logout

        binding.btnLogout.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}