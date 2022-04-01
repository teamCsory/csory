package com.example.csory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        second.setOnClickListener {

            // MainActivity에서 넘어온 Activity를 종료하고 싶으면
            finish()
        }
    }
}