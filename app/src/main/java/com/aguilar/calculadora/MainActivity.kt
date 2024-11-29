package com.aguilar.calculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CalculatorViewModel
    private lateinit var inputNumber: EditText
    private lateinit var resultTextView: TextView
    private lateinit var operationTextView: TextView
    private var lastOperation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNumber = findViewById(R.id.inputNumber)
        resultTextView = findViewById(R.id.resultTextView)
        operationTextView = findViewById(R.id.operationTextView)

        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

        // Observa los cambios en el resultado
        viewModel.result.observe(this) { result ->
            resultTextView.text = result.toString()
        }

        // Observa los cambios en la cadena de operaciones
        viewModel.operationStringLiveData.observe(this) { operationString ->
            operationTextView.text = operationString
        }

        // Configurar los botones de operación
        findViewById<Button>(R.id.addButton).setOnClickListener {
            performOperation("+")
        }
        findViewById<Button>(R.id.subtractButton).setOnClickListener {
            performOperation("-")
        }
        findViewById<Button>(R.id.multiplyButton).setOnClickListener {
            performOperation("*")
        }
        findViewById<Button>(R.id.divideButton).setOnClickListener {
            performOperation("/")
        }

        findViewById<Button>(R.id.clearButton).setOnClickListener {
            viewModel.clear()
            inputNumber.text.clear() // Limpiar el campo de entrada
        }
    }

    private fun performOperation(operation: String) {
        val number = inputNumber.text.toString()
        if (number.isNotEmpty()) {
            // Si hay una operación anterior, realiza el cálculo
            lastOperation?.let { lastOp ->
                viewModel.performOperation(number.toDouble(), lastOp)
            }

            // Guarda la nueva operación
            lastOperation = operation
            inputNumber.text.clear() // Limpiar el campo de entrada
        }
    }
}
