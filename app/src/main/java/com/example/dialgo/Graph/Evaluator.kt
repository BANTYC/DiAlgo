package com.example.dialgo.Graph

import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.Function
import kotlin.math.exp

class Evaluator(var expression: String) {
    private var f: Function

    init {
        val indexEq: Int = expression.indexOf("=")
        if (indexEq > -1) {
            val leftPart = expression.substring(0, indexEq)
            val rightPart = normalize(indexEq, expression)
            expression = leftPart + rightPart
        }
        f = Function("f(x) = $expression")

    }

    fun eval(x: Double): Double {
        val e = Expression("f($x)", f)
        return e.calculate()
    }

    private fun normalize(index: Int, expression: String): String {
        val rightPart: String = expression.substring(index + 1)
        rightPart.replace(" ", "")
        return "-($rightPart)"
    }
}