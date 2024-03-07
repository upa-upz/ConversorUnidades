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

    val default = arrayListOf("")
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

    var inputFormatA:String = ""
    var inputFormatB:String = ""

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

            override fun onNothingSelected(parentView: AdapterView<*>) {}
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

            override fun onNothingSelected(parentView: AdapterView<*>) {}
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

            override fun onNothingSelected(parentView: AdapterView<*>) {}
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

            override fun onNothingSelected(parentView: AdapterView<*>) {}
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
                inputFormatA = spinner2.selectedItem.toString()
                inputFormatB = spinner4.selectedItem.toString()
                edB.setText(convertirValor(inputFormatA,inputFormatB,textA).toString())
            }
        }

        var textWatcherB: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                textB = editable.toString()
                inputFormatA = spinner2.selectedItem.toString()
                inputFormatB = spinner4.selectedItem.toString()
                edA.setText(convertirValor(inputFormatB,inputFormatA,textB).toString())
            }
        }

        // Listeners de los EditText
        edA.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                edA.addTextChangedListener(textWatcherA)
                edB.removeTextChangedListener(textWatcherB)
            }
        }
        edB.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                edB.addTextChangedListener(textWatcherB)
                edA.removeTextChangedListener(textWatcherA)
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

    private fun convertirValor(formatInput:String = "", formaOutput:String = "", value:String = "0.0"):Double{
        var result:Double = 0.0
        var num:Double

        if(value == null || value == ""){
            num = 0.0
        }else {
            num = value.toDouble()
        }

                //in = km
        if(formatInput == "km"){
            when(formaOutput){
                "km" -> result = num
                "m" -> result = num * 1000
                "cm"-> result = num * 100000
                "mm" -> result = num * 1000000
                "mi" -> result = num / 1.609
                "yd" -> result = num * 1094
                "in" -> result = num * 39370
            }
        }else if(formatInput == "m"){
            when(formaOutput){
                "km" -> result = num /1000
                "m" -> result = num
                "cm"-> result = num * 100
                "mm" -> result = num * 1000
                "mi" -> result = num / 1609
                "yd" -> result = num * 1.094
                "in" -> result = num *  39.37
            }
        }else if(formatInput == "cm"){
            when(formaOutput){
                "km" -> result = num /100000
                "m" -> result = num / 100
                "cm"-> result = num
                "mm" -> result = num * 10
                "mi" -> result = num / 160900
                "yd" -> result = num / 91.44
                "in" -> result = num / 2.54
            }
        }else if(formatInput == "mm"){ // Milimetro
            when(formaOutput){
                "km" -> result = num /1000000 // kilometro
                "metro" -> result = num / 1000 // Metro
                "cm"-> result = num / 10 // Centimetro
                "mm" -> result = num // Milimetro
                "mi" -> result = num / 1609000 // Milla
                "yd" -> result = num / 914.4 // Yarda
                "in" -> result = num / 25.4 // Pulgada
            }
        }else if(formatInput == "mi"){ // Milla
            when(formaOutput){
                "km" -> result = num * 1.609  // kilometro
                "m" -> result = num * 1609 // Metro
                "cm"-> result = num * 160900 // Centimetro
                "mm" -> result = num * 1609000 // Milimetro
                "mi" -> result = num // Milla
                "yd" -> result = num * 1760 // Yarda
                "in" -> result = num * 1760 // Pulgada
            }
        }else if(formatInput == "yd"){ // Yarda
            when(formaOutput){
                "km" -> result = num / 1094 // kilometro
                "m" -> result = num / 1.094 // Metro
                "cm"-> result = num * 91.44 // Centimetro
                "mm" -> result = num * 914.4 // Milimetro
                "mi" -> result = num / 914.4 // Milla
                "yd" -> result = num // Yarda
                "in" -> result = num / 25.4 // Pulgada
            }
        }else if(formatInput == "in"){
            when(formaOutput){
                "km" -> result = num / 39370
                "m" -> result = num / 39.37
                "cm"-> result = num / 2.54
                "mm" -> result = num * 25.4
                "mi" -> result = num / 63360
                "yd" -> result = num / 36
                "in" -> result = num
            }
        }

        return  result
    }

}