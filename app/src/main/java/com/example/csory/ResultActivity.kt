package com.example.csory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ResultActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        if(intent.hasExtra("totalCorrect")){
            val totalCorrect = intent.getStringExtra("totalCorrect")
        }
    }
}