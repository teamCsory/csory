package com.example.csory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_discrimination.*
import kotlinx.android.synthetic.main.activity_word.*

class WordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        btn_twoword.setOnClickListener{
            val myIntent = Intent(this, TwowordActivity::class.java)
            startActivity(myIntent)
        }

        btn_threeword.setOnClickListener{
            val myIntent = Intent(this, ThreewordActivity::class.java)
            startActivity(myIntent)
        }

        btn_fourword.setOnClickListener{
            val myIntent = Intent(this, FourwordActivity::class.java)
            startActivity(myIntent)
        }
    }
}