package com.example.csory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_information.*
import kotlinx.android.synthetic.main.activity_main.*

class InformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        greennext.setOnClickListener {
            val myIntent = Intent(this, FunctionActivity::class.java)
            startActivity(myIntent)
            overridePendingTransition(0, 0);
        }
    }
}