package com.example.csory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_discrimination.*

class TwowordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twoword)

        word.setOnClickListener{
            val myIntent = Intent(this, WordActivity::class.java)
            startActivity(myIntent)
        }

    }
}