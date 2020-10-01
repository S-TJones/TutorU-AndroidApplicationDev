package com.example.androidapplicationdev

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.wearable.activity.WearableActivity
import androidx.core.content.ContextCompat.startActivity

class SplashScreen : WearableActivity() {

    private val LOAD_TIME: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent (this,MainActivity::class.java))
            finish()
        }, LOAD_TIME)

        // Enables Always-on
        setAmbientEnabled()
    }
}