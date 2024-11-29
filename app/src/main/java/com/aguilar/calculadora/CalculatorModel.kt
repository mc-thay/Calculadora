package com.aguilar.calculadora


class CalculatorModel {
    private var currentResult: Double = 0.0
    private var currentOperator: String = ""

    fun setNumber(number: Double) {
        currentResult = number
    }

    fun calculate(number: Double, operator: String): Double {
        currentOperator = operator
        return when (operator) {
            "+" -> currentResult + number
            "-" -> currentResult - number
            "*" -> currentResult * number
            "/" -> if (number != 0.0) currentResult / number else Double.NaN
            else -> currentResult
        }
    }

    fun getCurrentResult() = currentResult
    fun reset() {
        currentResult = 0.0
        currentOperator = ""
    }
}