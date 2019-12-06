package com.example.dialgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import com.example.dialgo.Algorithm.HalfDivAlgo
import com.example.dialgo.Graph.Evaluator
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_algorithm.*

class AlgorithmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algorithm)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Алгоритм"

        val x0Input:TextInputLayout = findViewById(R.id.a0)
        val x1Input:TextInputLayout = findViewById(R.id.a1)
        val funcInput:TextInputLayout = findViewById(R.id.funAlgo)
        val eps:TextInputLayout = findViewById(R.id.eps)
        val start:ImageButton = findViewById(R.id.startAlgo)
        val acc:EditText = findViewById(R.id.acc)
        val ans:EditText = findViewById(R.id.ans)

        start.setOnClickListener{
            if(checkFunc(funcInput)&&checkX(x0Input,x1Input)&&checkIter(eps)){
                val expression:String = funcInput.editText!!.text.toString()
                val epsDouble:Double = eps.editText!!.text.toString().toDouble()
                val algo = HalfDivAlgo(Evaluator(expression),epsDouble)
                val x0 = x0Input.editText!!.text.toString().toDouble()
                val x1 = x1Input.editText!!.text.toString().toDouble()

                ans.setText(algo.perform(x0,x1).toString())
                if(!algo.isOk()){
                    ans.setText("Неправильные x")
                }
                acc.setText(algo.getAccuracy().toString())
            }
        }
    }

    private fun checkX(x0: TextInputLayout, x1: TextInputLayout):Boolean{
        val x0Length = x0.editText?.length()?: 0
        val x1Length = x1.editText?.length()?: 0

        if(x0Length == 0) {
            x0.error = getString(R.string.err_x_length)
            return false
        }
        else{
            x0.error = null
        }
        if(x1Length == 0) {
            x1.error = getString(R.string.err_x_length)
            return false
        }
        else{
            x1.error = null
        }
        val x0Value:Double = x0.editText?.text.toString().toDouble()
        val x1Value:Double= x1.editText?.text.toString().toDouble()
        if(x1Value <= x0Value) {
            x1.error = getString(R.string.err_x1_lower_x0)
            return false
        }
        else{
            x1.error = null
            x0.error = null
        }
        return true
    }

    private fun checkFunc(funcInput: TextInputLayout):Boolean{
        val funcLength:Int = funcInput.editText?.length()?: 0
        return if (funcLength == 0) {
            funcInput.error = getString(R.string.err_func_length)
            false
        } else{
            funcInput.error = null
            true
        }
    }

    private fun checkIter(eps:TextInputLayout):Boolean{
        val epsLength:Int = eps.editText?.length()?: 0
        return if (epsLength == 0 || eps.editText?.text.toString().toDouble() < 0) {
            eps.error = "Введите корректное значение"
            false
        } else{
            eps.error = null
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_swap){
            startActivity(Intent(this, MainActivity::class.java))
        }
        return true
    }
}
