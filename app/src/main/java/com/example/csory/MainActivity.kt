package com.example.csory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        first.setOnClickListener{
            val myIntent = Intent(this, discriminationActivity::class.java)
            startActivity(myIntent)
        }


        second.setOnClickListener {
            val myIntent = Intent(this, voiceActivity::class.java)
            startActivity(myIntent)
        }

        info.setOnClickListener {
            val myIntent = Intent(this, InformationActivity::class.java)
            startActivity(myIntent)
        }
    }
}

