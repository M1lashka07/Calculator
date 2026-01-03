package com.example.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.mariuszgromada.math.mxparser.Expression
import kotlin.random.Random

class CalculatorViewModel : ViewModel() {


    private val _state: MutableStateFlow<CalculatorState> = MutableStateFlow(
        CalculatorState.Initial
    )
    val state = _state.asStateFlow()

    private var expression: String = ""

    fun processCommandInput(command: CalculatorCommand) {
        Log.d("CalculatorViewModel", "Command: $command")
        when (command) {
            CalculatorCommand.Clear -> {
                _state.value = CalculatorState.Initial
                expression = ""
            }

            CalculatorCommand.Evaluate -> {
                val result = evaluate()
                _state.value = if (result != null) CalculatorState.Success(result) else CalculatorState.Error(expression)
            }

            is CalculatorCommand.Input -> {
                val symbol = if (command.symbol != Symbol.PARENTHESIS) command.symbol.value
                else getCorrectParenthesis()

                expression += symbol

                _state.value = CalculatorState.Input(
                    expression = expression,
                    result = evaluate() ?: ""
                )
            }
        }
    }

    private fun evaluate(): String? {
        return expression.replace('x', '*')
            .replace(',', '.')
            .let { Expression(it) }
                .calculate()
                .takeIf { it.isFinite() }?.toString()
    }

    private fun getCorrectParenthesis(): String {
        val openCount = expression.count { it == '(' }
        val closeCount = expression.count { it == ')' }
        return when {
            expression.isEmpty() -> "("
            expression.last().let { !it.isDigit() && it != ')' && it != 'π' } -> "("
            openCount > closeCount -> ")"
            else -> "("
        }
    }
}

sealed interface CalculatorState {

    data object Initial : CalculatorState
    data class Input(
        val expression: String,
        val result: String
    ) : CalculatorState

    data class Success(val result: String) : CalculatorState

    data class Error(val expression: String) : CalculatorState
}

sealed interface CalculatorCommand {

    data object Clear : CalculatorCommand {}
    data object Evaluate : CalculatorCommand {}
    data class Input(val symbol: Symbol) : CalculatorCommand {}
}

enum class Symbol(val value: String) {

    DIGIT_0(value = "0"),
    DIGIT_1(value = "1"),
    DIGIT_2(value = "2"),
    DIGIT_3(value = "3"),
    DIGIT_4(value = "4"),
    DIGIT_5(value = "5"),
    DIGIT_6(value = "6"),
    DIGIT_7(value = "7"),
    DIGIT_8(value = "8"),
    DIGIT_9(value = "9"),
    ADD(value = "+"),
    SUBTRACT(value = "-"),
    MULTIPLY(value = "x"),
    DIVIDE(value = "÷"),
    PERCENT(value = "%"),
    POWER(value = "^"),
    FACTORIAL(value = "!"),
    SQRT(value = "√"),
    PI(value = "π"),
    DOT(value = ","),
    PARENTHESIS(value = "()")
}

data class Display(
    val expression: String,
    val result: String
)