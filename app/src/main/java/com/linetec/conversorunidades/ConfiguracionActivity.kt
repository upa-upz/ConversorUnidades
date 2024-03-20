package com.linetec.conversorunidades

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

class ConfiguracionActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var adapter: ArrayAdapter<String>

    private lateinit var switch: Switch

    var leguajeSeleccionado: String = ""
    var leguajeApp: String = ""
    var position:Int = 0

    var modoOscuro:Boolean = false
    var idioma:String = "0"

    var isUserInteracted:Boolean = false


    val idiomas = arrayListOf<String>("Español", "Ingles", "Portuges")
    private lateinit var btnVover: Button
    //private lateinit var modoNoche:Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)

        btnVover = findViewById<Button>(R.id.btn_config_volver)
        switch = findViewById(R.id.sw_config)
        spinner = findViewById(R.id.sp_config)
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, idiomas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        cargarDatos()

        switch.setOnCheckedChangeListener { _, isSelected ->
            guardarModoNoche(isSelected)
            /*if (isSelected) {
                // Seleccionado
                enableDarkMode()
            } else {
                // No Seleccionado
                disableDarkMode()
            }
            recreate()*/
        }


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                guardarLenguaje(position.toString())
                /*if(isUserInteracted){
                    //guardarLenguaje(position.toString())
                    Toast.makeText(this@ConfiguracionActivity,idiomas[position],Toast.LENGTH_SHORT).show()
                    when (position) {
                        0 -> setLocale("es")
                        1 -> setLocale("en")
                        2 -> setLocale("pt")
                    }
                }
                isUserInteracted = true*/

                /*if(defaultIdioma == parent.){

                }*/

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnVover.setOnClickListener {
            finish()
            /*val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)*/
        }
    }

    private fun cargarDatos() {
        // Cargar Idioma
        var sharedPreferences = getSharedPreferences("idioma", Context.MODE_PRIVATE)
        val idiomaActual = sharedPreferences.getString("idioma", "")
        val idioma:Int
        if(idiomaActual == null){
            idioma = 0
        }else {
            idioma = idiomaActual.toInt()
        }
        spinner.setSelection(idioma)

        // Cargar Modo Nocturno
        sharedPreferences = getSharedPreferences("modoNoche",Context.MODE_PRIVATE)
        var modoNoche = sharedPreferences.getBoolean("modoNoche",false)
        switch.setChecked(modoNoche)
    }
    private fun guardarLenguaje(value:String) {
        idioma = value
        val sharedPreferences = getSharedPreferences("idioma", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("idioma", idioma)
        editor.apply()
    }
    private fun guardarModoNoche(value:Boolean){
        modoOscuro = value
        val sharedPreferences = getSharedPreferences("modoNoche",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("modoNoche",modoOscuro)
        editor.apply()
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

    fun setLocale(localeName: String) {
        val locale = Locale(localeName)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        recreate()
    }
}