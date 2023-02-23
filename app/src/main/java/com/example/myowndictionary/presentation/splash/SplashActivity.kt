package com.example.myowndictionary.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.myowndictionary.R
import com.example.myowndictionary.presentation.authentication.logIn.LogInActivity
import com.example.myowndictionary.presentation.splash.components.SplashScreen
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setContent {
            SplashScreen()
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
        }
        startActivity(Intent(this,LogInActivity::class.java))
        finish()
    }

}