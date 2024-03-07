package com.linetec.conversorunidades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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

class PesoActivity : AppCompatActivity() {

    // kl = Kilogramo
    // g = gramo
    // lb = libra
    // oz = onza

    val default = arrayListOf("")
    val metrico = arrayListOf("kl", "g")
    val imperial = arrayListOf("lb", "oz")
    val medidas = arrayListOf("Metrico", "Imperial")

    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private lateinit var spinner4: Spinner

    private lateinit var adapter1: ArrayAdapter<String>
    private lateinit var adapter2: ArrayAdapter<String>
    private lateinit var adapter3: ArrayAdapter<String>
    private lateinit var adapter4: ArrayAdapter<String>

    private lateinit var textFirst: TextView
    private lateinit var textSecond: TextView

    private var inputFormatA:String = ""
    private var inputFormatB:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peso)

        val btnVolver = findViewById<Button>(R.id.btn_peso_volver)

        val edA = findViewById<EditText>(R.id.et_peso_first)
        val edB = findViewById<EditText>(R.id.et_peso_second)

        var textA = ""
        var textB = ""

        spinner1 = findViewById(R.id.sp_peso_1)
        spinner2 = findViewById(R.id.sp_peso_2)
        spinner3 = findViewById(R.id.sp_peso_3)
        spinner4 = findViewById(R.id.sp_peso_4)

        textFirst = findViewById(R.id.tv_peso_1)
        textSecond = findViewById(R.id.tv_peso_2)

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

        var textWatcherA: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                textA = editable.toString()
                inputFormatA = spinner2.selectedItem.toString()
                inputFormatB = spinner4.selectedItem.toString()
                //edB.setText(textA)
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
                //edA.setText(textB)
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

        btnVolver.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
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

        // kl = Kilogramo
        // g = gramo
        // lb = libra
        // oz = onza

        var result:Double = 0.0
        var num:Double

        if(value == null || value == ""){
            num = 0.0
        }else {
            num = value.toDouble()
        }

        //in = km
        if(formatInput == "kl"){ // Kilogramo
            when(formaOutput){
                "kl" -> result = num // Kilogramo
                "g" -> result = num * 1000 // Gramo
                "lb"-> result = num * 2.205 // Libra
                "oz" -> result = num * 35.274 // Onza
            }
        }else if(formatInput == "g"){ // Gramo
            when(formaOutput){
                "kl" -> result = num / 1000// Kilogramo
                "g" -> result = num // Gramo
                "lb"-> result = num / 453.6 // Libra
                "oz" -> result = num / 28.35 // Onza
            }
        }else if(formatInput == "lb"){ // Libra
            when(formaOutput){
                "kl" -> result = num / 2.205 // Kilogramo
                "g" -> result = num / 453.6 // Gramo
                "lb"-> result = num // Libra
                "oz" -> result = num * 16 // Onza
            }
        }else if(formatInput == "oz"){ // Onza
            when(formaOutput){
                "kl" -> result = num / 35.274 // Kilogramo
                "g" -> result = num * 28.35 // Gramo
                "lb"-> result = num / 16 // Libra
                "oz" -> result = num // Onza
            }
        }

        return  result
    }
}
