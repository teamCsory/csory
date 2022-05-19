package com.example.csory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayoutStates.TAG
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_discrimination.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private val TAG: String = "ResultActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        if (intent.hasExtra("keykey")) {
            val totalCorrect = intent.getStringExtra("keykey")
            val IntCorrect = Integer.parseInt(totalCorrect)
            var kk = IntCorrect.toFloat()
            val percent: Int = (kk / 12 * 100).toInt()

            //totaltextview.text = ("12문제중 $totalCorrect 문제를\n 맞췄음으로\n $percent % 의 정반응률을 보였습니다")
            totaltextview.text = ("\n\n정답수 : $totalCorrect / 12\n" +
                                    "정반응률 : $percent %\n\n" +
                                    "-----------------------\n\n" +
                                    "반복해서\n학습해보세요!")
        }
        phreturn.setOnClickListener{
            val myIntent = Intent(this, CategoryActivity::class.java)
            startActivity(myIntent)
            overridePendingTransition(0, 0);
        }

        comebackhome.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }



    }
}