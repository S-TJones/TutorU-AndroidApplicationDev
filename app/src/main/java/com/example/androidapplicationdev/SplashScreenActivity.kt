package com.example.androidapplicationdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Handler to make splash screen wait 3 seconds - Part 1
        val handler = Handler();
        handler.postDelayed({
            // Intent to open the main activity
            val openMain = Intent(this, MainActivity::class.java);
            startActivity(openMain);
        }, 13000) // <--- Time in milliseconds
        finish();

        // Handler to make splash screen wait 3 seconds - Part 2
        //iv_note.alpha = 0f
        //iv_note.animate().setDuration(1500).alpha(1f).withEndAction {
            //val openMain = Intent(this, MainActivity::class.java);
            //startActivity(openMain);
            //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            //finish()
        //}
    }
}