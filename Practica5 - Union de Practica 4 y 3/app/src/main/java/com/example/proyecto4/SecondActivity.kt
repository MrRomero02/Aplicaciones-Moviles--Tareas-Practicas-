package com.example.proyecto4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
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
        // Configura la Toolbar como ActionBar
        val toolbar = findViewById<Toolbar>(R.id.toolbar) // Asegúrate de que el ID sea correcto.
        setSupportActionBar(toolbar)
        binding.btnLogout.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 6. Implementar el cambio de divisas de Soles a Dólares
        binding.btnConvert.setOnClickListener {
            val soles = binding.etSoles.text.toString().trim()

            if (soles.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un valor en soles", Toast.LENGTH_LONG).show()
            } else {
                try {
                    val solesValue = soles.toDouble()
                    val conversionRate = 0.27 // Tipo de cambio de Soles a Dólares (puedes actualizarlo según el valor actual)
                    val dollarsValue = solesValue * conversionRate
                    binding.tvConversionResult.text = "Equivalente en dólares: $%.2f".format(dollarsValue)
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Por favor ingresa un valor numérico válido", Toast.LENGTH_LONG).show()
                }
            }
        }

        // 7. Implementar el cambio de divisas de Dólares a Soles
        binding.btnConvertToSoles.setOnClickListener {
            val dollars = binding.etDollars.text.toString().trim()

            if (dollars.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un valor en dólares", Toast.LENGTH_LONG).show()
            } else {
                try {
                    val dollarsValue = dollars.toDouble()
                    val conversionRate = 3.70 // Tipo de cambio de Dólares a Soles (puedes actualizarlo según el valor actual)
                    val solesValue = dollarsValue * conversionRate
                    binding.tvConversionResult.text = "Equivalente en soles: S/.%.2f".format(solesValue)
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Por favor ingresa un valor numérico válido", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

