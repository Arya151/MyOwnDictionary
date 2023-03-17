package com.example.myowndictionary.presentation.authentication.logIn

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myowndictionary.R
import com.example.myowndictionary.presentation.authentication.logIn.components.LogInScreen
import com.example.myowndictionary.presentation.home.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private val authViewModel by viewModels<AuthenticationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            authViewModel.updateScreenNumber(3)
            authViewModel.setTotalDigitsOfOtp(6)
            LogInScreen(this, FirebaseAuth.getInstance(), authViewModel)
        }

        authViewModel.currentScreen.observe(this, Observer {
            if (it == 3) {
                MainActivity.startActivity(this)
            }
        })
    }
}