package com.linetec.conversorunidades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class ConfiguracionActivity : AppCompatActivity() {

    private lateinit var spinner:Spinner
    private lateinit var adapter:ArrayAdapter<String>

    val idiomas = arrayListOf<String>("Español","Ingles","Portuges")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)

        spinner = findViewById(R.id.sp_config)
        adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,idiomas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }


        val btnVover = findViewById<Button>(R.id.btn_config_volver)

        btnVover.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}