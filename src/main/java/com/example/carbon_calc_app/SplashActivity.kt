package com.example.carbon_calc_app

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        val carbonCalcTextView = findViewById<TextView>(R.id.carbonCalcText)
        val paint = carbonCalcTextView.paint
        val width = paint.measureText(carbonCalcTextView.text.toString())
        val shader = LinearGradient(
            0f, 0f, width, 0f,
            intArrayOf(
                Color.parseColor("#4647ED"),
                Color.parseColor("#1E1E1E")
            ),
            null,
            Shader.TileMode.CLAMP
        )
        carbonCalcTextView.paint.shader = shader

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },3000)
    }
}