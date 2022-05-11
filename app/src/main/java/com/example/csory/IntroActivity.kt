package com.example.csory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import android.os.Handler


class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        var handler = Handler()
        handler.postDelayed({var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 1000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }



    }
