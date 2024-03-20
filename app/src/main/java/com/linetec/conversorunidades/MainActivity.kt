package com.linetec.conversorunidades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread.sleep(800)
        screenSplash.setKeepOnScreenCondition{ false }

        val btnConfig = findViewById<ImageButton>(R.id.btn_menu_config)
        val btnPeso = findViewById<ImageButton>(R.id.btn_menu_peso)
        val btnDistancia = findViewById<ImageButton>(R.id.btn_menu_distancia)
        val btnTemperatura = findViewById<ImageButton>(R.id.btn_menu_temperatura)

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
