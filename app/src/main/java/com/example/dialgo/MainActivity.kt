package com.example.dialgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart

import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        val x0Input:TextInputLayout = findViewById(R.id.x0)
        val x1Input:TextInputLayout = findViewById(R.id.x1)
        val funcInput:TextInputLayout = findViewById(R.id.func)
        val step:TextInputLayout = findViewById(R.id.step)
        val startButton:Button = findViewById(R.id.start)
        val lineChart:LineChart = findViewById(R.id.chart)
        val graph = Graph(lineChart,this)
        startButton.setOnClickListener{

            if(checkFunc(funcInput)&&checkX(x0Input,x1Input)){
                val evaluator:Evaluator = Evaluator(funcInput.editText!!.text.toString())
                graph.clear()
                var x0 = x0Input.editText!!.text.toString().toDouble()
                var x1 = x1Input.editText!!.text.toString().toDouble()
                val stepDouble = step.editText!!.text.toString().toDouble()
                var x = x0;

                while (x <= x1){
                    graph.addData(x.toFloat(), evaluator.eval(x).toFloat())
                    x+=stepDouble;
                }
                graph.drawLineChart()
            }
        }

    }
    fun checkStep(step:TextInputLayout):Boolean{
        val stepLength:Int = step.editText?.length()?: 0
        if(stepLength == 0) {
            step.error = getString(R.string.err_step)
            return false
        }
        if (step.editText!!.text.toString().toDouble() == 0.0){
            step.error = getString(R.string.err_step)
            return false
        }
        return true;
    }
    fun checkX(x0:TextInputLayout,x1:TextInputLayout):Boolean{
        val x0Length = x0.editText?.length()?: 0
        val x1Length = x1.editText?.length()?: 0

        if(x0Length == 0) {
            x0.error = getString(R.string.err_x_length)
            return false
        }
        if(x1Length == 0) {
            x1.error = getString(R.string.err_x_length)
            return false
        }

        val x0Value:Double = x0.editText?.text.toString().toDouble()
        val x1Value:Double= x1.editText?.text.toString().toDouble()
        if(x0Length > 0 && x1Length > 0 &&  x1Value <= x0Value) {
            x0.error = getString(R.string.err_x1_lower_x0)
            return false
        }
        return true
    }
    fun checkFunc(funcInput:TextInputLayout):Boolean{
        val funcLength:Int = funcInput.editText?.length()?: 0

        if (funcLength == 0) {
            funcInput.error = getString(R.string.err_func_length)
            return false
        }
        return true
    }
}
