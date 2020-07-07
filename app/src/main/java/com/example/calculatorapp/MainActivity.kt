package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDecimal : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear (view : View){
        tvInput.text = ""
        lastNumeric = false
        lastDecimal = false
    }

    fun onDecimal(view: View){
        if(!lastDecimal  && lastNumeric){
            tvInput.append(".")
        }
    }

    fun removeZeroAfterDot(result:String):String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0,result.length -2 )
        return value
    }
    fun onEqual(view: View){
        if(lastNumeric){ //for such inputs : -34- , 23- etc correct inputs 23-2,-12-3
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    //this if will make the input -234 to 234 ignoring starting -
                    prefix = "-"
                    tvValue = tvValue.substring(1)//-215 // ignores - sign at start
                }


                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    //for rg : 23-244
                    var one = splitValue[0] //has val 23
                    var two = splitValue[1] //has val 244

                    //if prefix is not empty then this tells that it has "-" so add it to one string var
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    //for rg : 23+244
                    var one = splitValue[0] //has val 23
                    var two = splitValue[1] //has val 244

                    //if prefix is not empty then this tells that it has "+" so add it to one string var
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }else if(tvValue.contains("x")){
                    val splitValue = tvValue.split("x")
                    //for rg : 23-244
                    var one = splitValue[0] //has val 23
                    var two = splitValue[1] //has val 244

                    //if prefix is not empty then this tells that it has "x" so add it to one string var
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    //for rg : 23-244
                    var one = splitValue[0] //has val 23
                    var two = splitValue[1] //has val 244

                    //if prefix is not empty then this tells that it has "/" so add it to one string var
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
            }catch(e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view : View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDecimal = false
        }
    }
    
    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("x") || value.contains("+") || value.contains("-")
        }
    }
    
    
}
