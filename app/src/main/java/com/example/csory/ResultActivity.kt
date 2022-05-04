package com.example.csory

import android.R
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*


class ResultActivity : AppCompatActivity() {

    var totalCorrectTextView: TextView? = null
    var correctIncorrectTextView: TextView? = null
    private var total: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //var t = findViewById<TextView>(R.id.total)
        var total = findViewById(R.id.totaltextview) as Textview


        //t = findViewById(R.id.total)

        if(intent.hasExtra("Correct")){
            val correct = intent.getIntExtra("Correct",0)
            total.setText("Correct")
            //t.setText(correct)


        }


    }
}