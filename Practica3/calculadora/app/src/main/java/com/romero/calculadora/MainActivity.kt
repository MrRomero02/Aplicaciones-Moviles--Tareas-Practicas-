package com.romero.calculadora

import android.os.Bundle
import android.text.TextUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.romero.calculadora.databinding.ActivityMainBinding
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        setupCalculator()
    }

    private fun setupCalculator() {
        binding.buttonAdd.setOnClickListener { performOperation(Operation.ADD) }
        binding.buttonSubtract.setOnClickListener { performOperation(Operation.SUBTRACT) }
        binding.buttonMultiply.setOnClickListener { performOperation(Operation.MULTIPLY) }
        binding.buttonDivide.setOnClickListener { performOperation(Operation.DIVIDE) }
         }

    private fun performOperation(operation: Operation) {
        val input1 = binding.editTextNumberA.text.toString()
        val input2 = binding.editTextNumberB.text.toString()

        if (TextUtils.isEmpty(input1) || (operation != Operation.SQUARE_ROOT && TextUtils.isEmpty(input2))) {
            binding.textViewResult.text = "Por favor, ingrese los valores necesarios"
            return
        }

        val number1 = input1.toDoubleOrNull()
        val number2 = input2.toDoubleOrNull()

        if (number1 == null || (operation != Operation.SQUARE_ROOT && number2 == null)) {
            binding.textViewResult.text = "Por favor, ingrese solo números válidos"
            return
        }

        val result = when (operation) {
            Operation.ADD -> number1 + number2!!
            Operation.SUBTRACT -> number1 - number2!!
            Operation.MULTIPLY -> number1 * number2!!
            Operation.DIVIDE -> if (number2 == 0.0) {
                binding.textViewResult.text = "Error: División por cero"
                return
            } else {
                number1 / (number2 ?: 1.0)
            }
            Operation.SQUARE_ROOT -> if (number1 < 0) {
                binding.textViewResult.text = "Error: Raíz cuadrada de un número negativo"
                return
            } else {
                sqrt(number1)
            }
            Operation.POWER -> number1.pow(number2!!)
        }

        binding.textViewResult.text = result.toString()
    }

    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, SQUARE_ROOT, POWER
    }
}
