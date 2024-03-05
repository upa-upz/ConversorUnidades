package com.linetec.conversorunidades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast

class DistanciaActivity : AppCompatActivity() {

    val metric = arrayListOf("km","m","cm","mm")
    val imperial = arrayListOf("mi","yd","in")

    var sipinner1_selected = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distancia)
        val context = applicationContext

        val btnVolver = findViewById<Button>(R.id.btn_dist_volver)

        // ----------------- SPINNER 1 ---------------
        val spinner1 = findViewById<Spinner>(R.id.sp_dist_1)
        val sp1_options = arrayOf("Metrico","Imperial")
        val adapter1 = ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sp1_options)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter1

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // Manejar la selección del Spinner aquí
                val opcionSeleccionada = sp1_options[position]
                sipinner1_selected = opcionSeleccionada
                //Toast.makeText(context, opcionSeleccionada, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Acciones cuando no hay nada seleccionado
            }
        }

        // ----------------- SPINNER 2 ---------------
        val spinner2 = findViewById<Spinner>(R.id.sp_dist_2)
        var adapter2:ArrayAdapter<String>
        if(sipinner1_selected == "Metrico"){
            adapter2 = ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,metric)
        }else {
            adapter2 = ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,imperial)
        }

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter2

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // Manejar la selección del Spinner aquí
                val opcionSeleccionada = sipinner1_selected[position]
                // Puedes realizar acciones según la opción seleccionada
                //Toast.makeText(context, opcionSeleccionada, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Acciones cuando no hay nada seleccionado
            }
        }
        // --------------------------------

        btnVolver.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}