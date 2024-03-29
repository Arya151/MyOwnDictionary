package com.example.myowndictionary.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.myowndictionary.presentation.home.components.HomeScreen

class MainActivity : AppCompatActivity() {

    companion object {
        fun startActivity(mContext: Context) {
            mContext.startActivity(Intent(mContext, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}