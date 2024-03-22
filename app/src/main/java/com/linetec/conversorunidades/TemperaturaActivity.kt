package com.linetec.conversorunidades

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class TemperaturaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        val btnVolver = findViewById<ImageButton>(R.id.btn_temp_volver)
        val edA = findViewById<EditText>(R.id.ed_temp_a)
        val edB = findViewById<EditText>(R.id.ed_temp_b)

        var textA = ""
        var textB = ""

        val context = applicationContext

         var textWatcherA: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                textA = editable.toString()
                edB.setText(tempConverTo(textA,1).toString())
            }
        }

        var textWatcherB: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                textB = editable.toString()
                edA.setText(tempConverTo(textB,0).toString())
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

        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun tempConverTo(value: String, formate: Int = 0): Float {

        //formato = 0 / Celcius / (10°F − 32) × 5/9 = -12.22°C
        //formato = 1 / Farenheig / (10°C × 9/5) + 32 = 50°F

        var number: Float

        if (value == "") {
            number = 0f
        } else {
            number = value.toFloat()
        }

        var resul = 0f

        if (formate == 1) {
            resul = (number * 9 / 5) + 32
        }
        if (formate == 0) {
            resul = (number - 32) * 5 / 9
        }
        return resul
    }
}