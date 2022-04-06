package com.example.csory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_voice.*
import java.util.*
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest

class phonemeActivity : AppCompatActivity(), View.OnClickListener, TextToSpeech.OnInitListener {


    private var btn_ga: Button? = null
    private var btn_phoneme: Button? = null
    private var btn_phoneme2: Button? = null
    private var btn_phoneme3: Button? = null
    private var btn_phoneme4: Button? = null
    private var tts: TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phoneme)

        //edit_readText = findViewById<View>(R.id.edit_readText) as EditText
        btn_phoneme = findViewById<View>(R.id.btn_phoneme) as Button
        btn_phoneme2 = findViewById<View>(R.id.btn_phoneme2) as Button
        btn_phoneme3 = findViewById<View>(R.id.btn_phoneme3) as Button
        btn_phoneme4 = findViewById<View>(R.id.btn_phoneme4) as Button
        btn_speech!!.isEnabled = false
        btn_speech!!.setOnClickListener(this)
        tts = TextToSpeech(this, this)
    }

    // 글자 읽어주기
    private fun Speech() {
        val text = edit_readText!!.text.toString().trim { it <= ' ' }
        tts!!.setPitch(1.0.toFloat()) // 음량
        tts!!.setSpeechRate(1.0.toFloat()) // 재생속도
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            // 작업 성공
            val language = tts!!.setLanguage(Locale.KOREAN) // 언어 설정
            if (language == TextToSpeech.LANG_MISSING_DATA
                || language == TextToSpeech.LANG_NOT_SUPPORTED
            ) {

                // 언어 데이터가 없거나, 지원하지 않는경우
                btn_speech!!.isEnabled = false
                Toast.makeText(this, "지원하지 않는 언어입니다.", Toast.LENGTH_SHORT).show()
            } else {

                // 준비 완료
                btn_speech!!.isEnabled = true
            }
        } else {

            // 작업 실패
            Toast.makeText(this, "TTS 작업에 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_speech -> Speech()
            else -> {
            }
        }
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}