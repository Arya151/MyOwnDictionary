package com.example.myowndictionary.presentation.authentication.logIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.myowndictionary.R
import com.example.myowndictionary.presentation.authentication.logIn.components.LogInScreen
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private val authViewModel by viewModels<AuthenticationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        setContent {
            authViewModel.updateScreenNumber(2)
            authViewModel.setTotalDigitsOfOtp(6)
            LogInScreen(this, FirebaseAuth.getInstance(),authViewModel)
        }
    }
}