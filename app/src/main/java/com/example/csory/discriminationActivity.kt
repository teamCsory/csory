package com.example.csory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_discrimination.*
import kotlinx.android.synthetic.main.activity_main.*

class discriminationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discrimination)

        btn_phoneme.setOnClickListener{
            val myIntent = Intent(this, CategoryActivity::class.java)
            startActivity(myIntent)
        }

    }
}