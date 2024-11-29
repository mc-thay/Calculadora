package com.aguilar.calculadora


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private var currentResult = 0.0
    private var operationString = ""

    private val _result = MutableLiveData<Double>().apply { value = currentResult }
    val result: LiveData<Double> get() = _result

    private val _operationString = MutableLiveData<String>().apply { value = operationString }
    val operationStringLiveData: LiveData<String> get() = _operationString

    // Método para realizar la operación
    fun performOperation(newNumber: Double, operation: String) {
        when (operation) {
            "+" -> {
                currentResult += newNumber
                operationString += "$newNumber + "
            }
            "-" -> {
                currentResult -= newNumber
                operationString += "$newNumber - "
            }
            "*" -> {
                currentResult *= newNumber
                operationString += "$newNumber * "
            }
            "/" -> {
                if (newNumber != 0.0) {
                    currentResult /= newNumber
                    operationString += "$newNumber / "
                } else {
                    operationString += "Error: Div by 0 "
                }
            }
        }
        _result.value = currentResult
        _operationString.value = operationString
    }

    // Método para limpiar
    fun clear() {
        currentResult = 0.0
        operationString = ""
        _result.value = currentResult
        _operationString.value = operationString
    }
}