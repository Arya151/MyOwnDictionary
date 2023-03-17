package com.example.myowndictionary.presentation.viewCollection

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.myowndictionary.presentation.createCollection.CreateCollectionActivity

class ViewCollectionActivity : AppCompatActivity() {

    companion object {
        fun startActivity(mContext: Context) {
            mContext.startActivity(Intent(mContext, ViewCollectionActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{

        }
    }
}