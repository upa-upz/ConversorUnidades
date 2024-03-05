package com.linetec.conversorunidades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener


class TemperaturaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        val btnVolver = findViewById<Button>(R.id.btn_temp_volver)
        val edA = findViewById<EditText>(R.id.ed_temp_a)
        val edB = findViewById<EditText>(R.id.ed_temp_b)

        var textA = ""
        var textB = ""

         val textWatcherA: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                textA = editable.toString()
                edB.setText(textA)
            }
        }

        val textWatcherB: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                textB = editable.toString()
                edB.setText(textB)
            }
        }

        edA.setOnClickListener {
            edB.removeTextChangedListener(textWatcherB)
            edA.addTextChangedListener(textWatcherA)
        }

        edB.setOnClickListener {
            edB.removeTextChangedListener(textWatcherA)
            edA.addTextChangedListener(textWatcherB)
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