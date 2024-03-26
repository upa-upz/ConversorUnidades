package com.linetec.conversorunidades

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
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
    var flag = true
    private lateinit var versionText:TextView

    var modoOscuro:Boolean = false
    var idioma:String = "0"
    var nightMode:Boolean = true

    var isUserInteracted:Boolean = false


    val idiomas = arrayListOf<String>("Español", "Ingles", "Portuges")
    private lateinit var btnVover: ImageButton
    //private lateinit var modoNoche:Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)
        var versionName = getVersionName()

        btnVover = findViewById<ImageButton>(R.id.btn_config_volver)
        switch = findViewById(R.id.sw_config)
        spinner = findViewById(R.id.sp_config)
        adapter = ArrayAdapter(this, R.layout.spinner_item, idiomas)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner.adapter = adapter
        versionText = findViewById(R.id.tv_conf_version)

        cargarDatos()

        versionText.text = "v$versionName"

        switch.setOnClickListener {
            if(switch.isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                guardarModoNoche(true)
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                guardarModoNoche(false)
            }
        }

        btnVover.setOnClickListener {
            /*val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)*/
            finish()
        }
    }

    private fun cargarDatos() {
        // Cargar Idioma
        var sharedPreferences = getSharedPreferences("idioma", Context.MODE_PRIVATE)
        //val idiomaActual = sharedPreferences.getString("idioma", "0")
        //val idioma:Int
        /*if(idiomaActual == null){
            idioma = 0
        }else {
            idioma = idiomaActual.toInt()
        }*/
        //spinner.setSelection(idioma)
        // Cargar Modo Nocturno
        sharedPreferences = getSharedPreferences("modoNoche",Context.MODE_PRIVATE)
        var modoNoche = sharedPreferences.getBoolean("modoNoche",false)
        switch.setChecked(modoNoche)
    }
    /*private fun guardarLenguaje(value:String) {
        idioma = value
        val sharedPreferences = getSharedPreferences("idioma", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("idioma", idioma)
        editor.apply()
    }*/
    private fun guardarModoNoche(value:Boolean){
        modoOscuro = value
        val sharedPreferences = getSharedPreferences("modoNoche",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("modoNoche",modoOscuro)
        editor.apply()
    }
    private fun getVersionName(): String {
        return try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            "N/A"
        }
    }

    /*private fun updateLenguaje(lang:String){
        //guardarLenguaje(lang)

        var locale = when (lang) {
            "Ingles" -> Locale("en")
            "Español" -> Locale("es")
            "Portuges" -> Locale("pt")
            else -> Locale.getDefault()
        }
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        this.createConfigurationContext(configuration)
        recreate()
    }*/
}