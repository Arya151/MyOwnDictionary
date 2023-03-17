package com.example.myowndictionary.presentation.createCollection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.myowndictionary.presentation.home.MainActivity

class CreateCollectionActivity : AppCompatActivity() {

    companion object {
        fun startActivity(mContext: Context) {
            mContext.startActivity(Intent(mContext, CreateCollectionActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}