package com.romero.conversiondinero

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.romero.conversiondinero.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Tasas de cambio (ejemplos)
    val exchangeRateSolesToDollars = 0.26 // 1 Sol = 0.26 USD
    val exchangeRateDollarsToSoles = 3.85 // 1 USD = 3.85 Soles

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonConvert.setOnClickListener {
            val amountText = binding.editTextAmount.text.toString()
            if (amountText.isNotEmpty()) {
                val amount = amountText.toDouble()
                val result: Double
                val resultText: String
                if (binding.radioButtonSToD.isChecked) {
                    // Convertir de Soles a Dólares
                    result = amount * exchangeRateSolesToDollars
                    resultText = "Resultado: $amount Soles = %.2f Dólares".format(result)
                } else if (binding.radioButtonDToS.isChecked) {
                    // Convertir de Dólares a Soles
                    result = amount * exchangeRateDollarsToSoles
                    resultText = "Resultado: $amount Dólares = %.2f Soles".format(result)
                } else {
                    resultText = "Por favor, seleccione una opción de conversión."
                }
                binding.textViewResult.text = resultText
            } else {
                binding.textViewResult.text = "Por favor, ingrese un monto válido."
            }
        }
    }
}
