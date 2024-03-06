package com.linetec.conversorunidades

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DistanciaActivity : AppCompatActivity() {

    //var sipinner1_selected = ""

    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private lateinit var spinner4: Spinner

    private lateinit var adapter1: ArrayAdapter<String>
    private lateinit var adapter2: ArrayAdapter<String>
    private lateinit var adapter3: ArrayAdapter<String>
    private lateinit var adapter4: ArrayAdapter<String>

    private lateinit var textFirst:TextView
    private lateinit var textSecond:TextView

    val default = arrayListOf("Eliga Opcion")
    val metrico = arrayListOf("km", "m", "cm", "mm")
    val imperial = arrayListOf("mi", "yd", "in")
    val medidas = arrayListOf("Metrico", "Imperial")

    // km = kilometro
    // m = metro
    // cm = centimetro
    // mm = milimetro
    // mi = milla
    // yd = yarda
    // in = pulgada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distancia)
        val context = applicationContext

        val btnVolver = findViewById<Button>(R.id.btn_dist_volver)

        val edA = findViewById<EditText>(R.id.et_dist_first)
        val edB = findViewById<EditText>(R.id.et_dist_second)

        var textA = ""
        var textB = ""

        spinner1 = findViewById(R.id.sp_dist_1)
        spinner2 = findViewById(R.id.sp_dist_2)
        spinner3 = findViewById(R.id.sp_dist_3)
        spinner4 = findViewById(R.id.sp_dist_4)

        textFirst = findViewById(R.id.tx_dist_first)
        textSecond = findViewById(R.id.tx_dist_second)

        adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, medidas)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter1

        adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, default)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter2

        adapter3 = ArrayAdapter(this,android.R.layout.simple_spinner_item,medidas)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = adapter3

        adapter4 = ArrayAdapter(this,android.R.layout.simple_spinner_item,default)
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner4.adapter = adapter4

        // ----------------- SPINNER 1 ---------------
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = medidas[position]
                updateSecondSpinnerOptions(selectedItem)
                updateText("in",textFirst)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // No se necesita implementación aquí
            }
        }
        // ----------------- SPINNER 2 ---------------
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                updateText(parentView.selectedItem.toString(),textFirst)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // No se necesita implementación aquí
            }
        }
        // ----------------- SPINNER 3 ---------------
        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = medidas[position]
                updateFourthSpinnerOptions(selectedItem)
                updateText("in",textSecond)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // No se necesita implementación aquí
            }
        }
        // ----------------- SPINNER 4 ---------------
        spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                updateText(parentView.selectedItem.toString(),textSecond)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // No se necesita implementación aquí
            }
        }
        // -------------------------------------------
        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var textWatcherA: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                textA = editable.toString()
                edB.setText(textA)
            }
        }

        var textWatcherB: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                textB = editable.toString()
                edA.setText(textB)
            }
        }
        edA.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                edA.addTextChangedListener(textWatcherA)
                edB.removeTextChangedListener(textWatcherB)
                //Toast.makeText(context, "Entró a A", Toast.LENGTH_SHORT).show()
            }
        }

        edB.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                edB.addTextChangedListener(textWatcherB)
                edA.removeTextChangedListener(textWatcherA)
                //Toast.makeText(context, "Entró a B", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun updateSecondSpinnerOptions(selectedItem: String) {
        val options: List<String> = when (selectedItem) {
            "Metrico" -> metrico
            "Imperial" -> imperial
            else -> listOf()
        }

        adapter2.clear()
        adapter2.addAll(options)
        adapter2.notifyDataSetChanged()
    }
    private fun updateFourthSpinnerOptions(selectedItem: String) {
        val options: List<String> = when (selectedItem) {
            "Metrico" -> metrico
            "Imperial" -> imperial
            else -> listOf()
        }

        adapter4.clear()
        adapter4.addAll(options)
        adapter4.notifyDataSetChanged()
    }

    private fun updateText(selectedItem: String, text: TextView){
        text.setText(selectedItem)
    }

    //Convertir de metrico a imperial
    private fun convertMetricToImperial(formato:String, value:String){

    }
    private fun convertImperialToMetric(formato:String, value:String){

    }
    private fun convertMetricToMetric(value:String,initFormat:String,finalFormat:String):String{
        var result:Double = 0.0

        var firstNum:Double = value.toDouble()
        var secondNum:Double = 0.0

        var firstValue:Int = 0
        var secondValue:Int = 0

        when (initFormat) {
            "km" -> firstValue = 1
            "m" -> firstValue = 10
            "cm" -> firstValue = 100
            "mm" -> firstValue = 1000
            else -> firstValue = 0
        }
        when (finalFormat) {
            "km" -> secondValue = 1
            "m" -> secondValue = 10
            "cm" -> secondValue = 100
            "mm" -> secondValue = 1000
            else -> secondValue = 0
        }

        if(firstValue > secondValue){
            result = firstNum * secondNum
        }else {
            result = firstNum / secondNum
        }

        return result.toString()
    }
    private fun convertImperialToImperial(){

    }

}