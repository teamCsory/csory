package com.example.csory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_discrimination.*

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        btn_first.setOnClickListener{
            val myIntent = Intent(this, phonemeActivity::class.java)
            startActivity(myIntent)
        }

        btn_second.setOnClickListener{
            val myIntent = Intent(this, SecondphActivity::class.java)
            startActivity(myIntent)
        }

        btn_third.setOnClickListener{
            val myIntent = Intent(this, ThirdphActivity::class.java)
            startActivity(myIntent)
        }

        btn_fourth.setOnClickListener{
            val myIntent = Intent(this, FourthphActivity::class.java)
            startActivity(myIntent)
        }

    }
}