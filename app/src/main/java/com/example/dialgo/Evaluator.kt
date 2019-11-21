package com.example.dialgo
import  org.mariuszgromada.math.mxparser.*
import org.mariuszgromada.math.mxparser.*
import org.mariuszgromada.math.mxparser.Function

class Evaluator(var expression: String) {
    private var f:Function = Function("f(x) = $expression")

    fun eval(x:Double):Double{
        val e:Expression = Expression("f($x)",f)
        return e.calculate()
    }
}