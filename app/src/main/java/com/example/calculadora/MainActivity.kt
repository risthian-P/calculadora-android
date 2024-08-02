package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    val suma = "+"
    val resta = "-"
    val multiplic = "*"
    val division = "/"

    var operacionActual = ""

    var primerNumero:Double = Double.NaN
    var segundoNumero:Double = Double.NaN

    lateinit var tv_2:TextView
    lateinit var tv_1:TextView

    lateinit var formatoDecimal:DecimalFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        // }
        formatoDecimal = DecimalFormat("#.###")
        tv_2 = findViewById(R.id.tv_2)
        tv_1 = findViewById(R.id.tv_1)
    }

    fun calcular (){
        try {
            if (primerNumero.toString() != "NaN"){
                if (tv_2.text.toString().isEmpty()){
                    tv_2.text = tv_1.text.toString()
                }
                segundoNumero = tv_2.text.toString().toDouble()
                tv_2.text = ""

                when(operacionActual){
                    "+" -> primerNumero = (primerNumero + segundoNumero)
                    "-" -> primerNumero = (primerNumero - segundoNumero)
                    "*" -> primerNumero = (primerNumero * segundoNumero)
                    "/" -> primerNumero = (primerNumero / segundoNumero)
                }
            }else{
                primerNumero = tv_2.text.toString().toDouble()
            }
        }catch (e:Exception){

        }
    }

    fun seleccionarNumero (b:View){
        var boton:Button = b as Button
        if (tv_2.text.toString() == "0"){
            tv_2.text = ""
        }
        tv_2.text = tv_2.text.toString() + boton.text.toString()

    }

    fun igual (b:View){
        calcular()
        tv_1.text = formatoDecimal.format(primerNumero)
        //primerNumero = Double.NaN
        operacionActual = ""
    }

    fun cambiaroperador (b:View){
        if (tv_2.text.isNotEmpty() || primerNumero.toString()!="NaN"){
            var boton:Button = b as Button
            calcular()
            operacionActual = boton.text.toString().trim()
            tv_1.text = formatoDecimal.format(primerNumero) + operacionActual
            tv_2.text = ""
        }
    }

    fun borrar (b:View){
        var boton:Button = b as Button
        if (boton.text.toString().trim()=="C"){
            if (tv_2.text.toString().isNotEmpty()){
                var datosActuales:CharSequence = tv_2.text as CharSequence
                tv_2.text = datosActuales.subSequence(0, datosActuales.length-1)
            }else{
                primerNumero = Double.NaN
                segundoNumero = Double.NaN
                tv_2.text = ""
                tv_1.text = ""
            }
        }
    }

}