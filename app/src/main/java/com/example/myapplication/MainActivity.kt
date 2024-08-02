package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    val Suma = "+"
    val Resta = "-"
    val Multiplicacion = "*"
    val Division = "/"
    val Seno = "sin"
    val Coseno = "cos"
    val Tangente = "tan"

    var operacionActual = ""
    var primerNumero:Double = Double.NaN
    var segundoNumero:Double = Double.NaN

    lateinit var tvTemp:TextView
    lateinit var tvResult: TextView

    lateinit var formatoDecimal:DecimalFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        formatoDecimal = DecimalFormat("#.######")

        tvTemp = findViewById(R.id.tvTemp)
        tvResult = findViewById(R.id.tvResult)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun cambiarOperador(b:View){
        if(tvTemp.text.isNotEmpty() || primerNumero.toString()!="NaN"){

            calcular()
            val boton:Button = b as Button
            if(boton.text.toString().trim()=="/"){
                operacionActual = "/"
            }else if(boton.text.toString().trim()=="*"){
                operacionActual = "*"
            }else{
                operacionActual = boton.text.toString().trim()
            }
            if(tvTemp.text.toString().isEmpty()){
                tvTemp.text=tvResult.text
            }

            tvResult.text = formatoDecimal.format(primerNumero) + operacionActual
            tvTemp.text =""
        }
    }

    fun calcular(){
        try {
            if(primerNumero.toString()!="NaN"){
                if(tvTemp.text.toString().isEmpty()){
                    tvTemp.text = tvResult.text.toString()
                }
                segundoNumero = tvTemp.text.toString().toDouble()
                tvTemp.text=""

                when(operacionActual){
                    "+"-> primerNumero = (primerNumero+segundoNumero)
                    "-"-> primerNumero = (primerNumero-segundoNumero)
                    "*"-> primerNumero = (primerNumero*segundoNumero)
                    "/"-> primerNumero = (primerNumero/segundoNumero)
                    "sin"-> primerNumero = sin(primerNumero)
                    "cos"-> primerNumero = cos(primerNumero)
                    "tan"-> primerNumero = tan(primerNumero)
                }
            }else{
                primerNumero = tvTemp.text.toString().toDouble()
            }
        }catch (e:Exception){

        }
    }

    fun seleccionarNumero(b: View){
        val boton:Button = b as Button
        tvTemp.text = tvTemp.text.toString() + boton.text.toString()
    }

    fun igual(b: View){
        calcular()
        tvResult.text = formatoDecimal.format(primerNumero)
        //primerNumero = Double.NaN
        operacionActual = ""
    }

    fun borrar(b:View){
        val boton:Button = b as Button
        if(boton.text.toString().trim()=="C"){

            if(tvTemp.text.toString().isNotEmpty()){
                var datosActuales:CharSequence = tvTemp.text as CharSequence
                tvTemp.text = datosActuales.subSequence(0,datosActuales.length-1)
            }else{
                primerNumero = Double.NaN
                segundoNumero = Double.NaN
                tvTemp.text = ""
                tvResult.text = ""
            }
        }
    }
}