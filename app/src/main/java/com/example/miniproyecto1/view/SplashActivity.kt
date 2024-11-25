package com.example.miniproyecto1.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.miniproyecto1.R
import android.widget.ImageView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val splashScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val bottleIcon: ImageView = findViewById(R.id.bottle_icon)
        Glide.with(this)
            .load(R.drawable.ic_bottle_anim)
            .into(bottleIcon)

        splashScope.launch {
            delay(3000)
            val intent = Intent(this@SplashActivity, LoginActivity::class.java) // Cambiado a MainActivity
            startActivity(intent)
            finish()
        }
    }
}


