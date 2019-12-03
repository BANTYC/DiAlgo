package com.example.dialgo.Algorith

import com.example.dialgo.Graph.Evaluator
import kotlin.math.abs
import kotlin.math.sign

class HalfDivAlgo (val evaluator: Evaluator){
    private var iteration:Int = 1
    private var result:Double = 0.0
    private var successful:Boolean = false

    fun perform( x1:Double, x2: Double, numberOfIterations:Int):Double{

        var x1 = x1
        var x2 = x2
        for (i in 1..numberOfIterations){
            if(checkToRoot(x1)) {
                return root(x1)
            }
            if (checkToRoot(x2)) {
                return root(x2)
            }
            val mid:Double = (x2 + x1) / 2
            if (checkToRoot(mid))
                return root(mid)
            if (checkFunSigns(x1,mid))
                x2 = mid
            else if(checkFunSigns(mid,x2)){
                x1 = mid
            }
        }
        successful = false
        return (x2 + x1)/2
    }

    private fun root(x2: Double): Double {
        successful = true
        return x2
    }

    fun checkToRoot(x1: Double):Boolean{
        val small = 0.000000000000001
        return abs(x1) <= small
    }

    fun checkFunSigns(x1: Double,x2: Double):Boolean{
        val sign1 = evaluator.eval(x1).sign
        val sign2 = evaluator.eval(x2).sign
        if(sign1 * sign2 ==  -1.0)
            return true
        else
            return false
    }
}