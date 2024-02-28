package com.linetec.conversorunidades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnConfig = findViewById<Button>(R.id.btn_menu_config)
        val btnPeso = findViewById<Button>(R.id.btn_menu_peso)
        val btnDistancia = findViewById<Button>(R.id.btn_menu_distancia)
        val btnTemperatura = findViewById<Button>(R.id.btn_menu_temperatura)

        btnPeso.setOnClickListener {
            val intent = Intent(this,PesoActivity::class.java)
            startActivity(intent)
        }
        btnDistancia.setOnClickListener {
            val intent = Intent(this, DistanciaActivity::class.java)
            startActivity(intent)
        }
        btnTemperatura.setOnClickListener {
            val intent = Intent(this,TemperaturaActivity::class.java)
            startActivity(intent)
        }
        btnConfig.setOnClickListener {
            val intent = Intent(this,ConfiguracionActivity::class.java)
            startActivity(intent)
        }
    }
}
