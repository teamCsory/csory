package com.example.csory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayoutStates.TAG
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_result.totaltextview
import kotlinx.android.synthetic.main.activity_resultword.*

class ResultwordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultword)

        if(intent.hasExtra("keykey")){
            val totalCorrect = intent.getStringExtra("keykey")
            val IntCorrect = Integer.parseInt(totalCorrect)
            var kk= IntCorrect.toFloat()
            val percent:Int = (kk/10*100).toInt()

            totaltextview.text = ("\n\n정답수 : $totalCorrect / 10\n" +
                    "정반응률 : $percent %\n\n" +
                    "-----------------------\n\n" +
                    "반복해서\n학습해보세요!")
        }
        wordreturn.setOnClickListener{
            val myIntent = Intent(this, WordActivity::class.java)
            startActivity(myIntent)
            overridePendingTransition(0, 0);
        }

        comebackhome2.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

    }

}