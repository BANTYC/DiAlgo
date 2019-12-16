package com.example.dialgo.Algorithm

import com.example.dialgo.Graph.Evaluator
import kotlin.math.abs
import kotlin.math.sign

class HalfDivAlgo (val evaluator: Evaluator, val eps:Double){
    private var iteration:Int = 0
    private var goodInput:Boolean = true
    private var accuracy = 0.0



    fun perform( firstPoint:Double, secondPoint: Double):Double{
        var x1 = firstPoint
        var x2 = secondPoint
        var mid = x1 / 2 + x2 / 2
        accuracy = abs(x2 - x1)
        if (!checkFunSigns(x1, x2)){
            goodInput = false
            return 0.0
        }
        while (accuracy > eps){
            when {
                checkToRoot(x1) -> return x1
                checkToRoot(x2) -> return x2

                checkFunSigns(x1, x2) ->{
                    mid = (x2 + x1) / 2
                    accuracy /= 2

                    if (abs(mid) < eps)
                        return mid
                    else
                        when{
                            checkFunSigns(x1, mid) -> x2 = mid
                            checkFunSigns(x2, mid) -> x1 = mid
                        }
                }
            }
        }
        return mid
    }

    fun getAccuracy() = accuracy

    fun isOk() = goodInput

    private fun checkToRoot(x1: Double):Boolean{
        return abs(evaluator.eval(x1)) < eps
    }

    private fun checkFunSigns(x1: Double, x2: Double):Boolean{
        val sign1 = evaluator.eval(x1).sign
        val sign2 = evaluator.eval(x2).sign
        return sign1 * sign2 ==  -1.0
    }


}