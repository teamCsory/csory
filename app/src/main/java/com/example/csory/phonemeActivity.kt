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
    private var tts: TextToSpeech? = null
    private var btn_phoneme: Button? = null
    private var btn_phoneme2: Button? = null
    private var btn_phoneme3: Button? = null
    private var btn_phoneme4: Button? = null

    var problems = arrayOf<HashMap<*, *>>(
        object : HashMap<Any?, Any?>() {
            init {
                put("question", "1 + 2 = ?")
                put("answer", "3")
                put("example1", "1")
                put("example2", "3")
                put("example3", "2")
                put("example4", "4")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("question", "3 + 2 = ?")
                put("answer", "5")
                put("example1", "4")
                put("example2", "6")
                put("example3", "5")
                put("example4", "2")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("question", "3 + 3 = ?")
                put("answer", "6")
                put("example1", "6")
                put("example2", "1")
                put("example3", "5")
                put("example4", "4")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("question", "0 + 3 = ?")
                put("answer", "3")
                put("example1", "1")
                put("example2", "2")
                put("example3", "4")
                put("example4", "3")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("question", "4 + 2 = ?")
                put("answer", "6")
                put("example1", "6")
                put("example2", "4")
                put("example3", "2")
                put("example4", "5")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("question", "5 + 4 = ?")
                put("answer", "9")
                put("example1", "8")
                put("example2", "6")
                put("example3", "7")
                put("example4", "9")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("question", "4 + 4 = ?")
                put("answer", "8")
                put("example1", "7")
                put("example2", "1")
                put("example3", "8")
                put("example4", "3")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("question", "2 + 5 = ?")
                put("answer", "7")
                put("example1", "7")
                put("example2", "1")
                put("example3", "5")
                put("example4", "4")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("question", "1 + 4 = ?")
                put("answer", "5")
                put("example1", "4")
                put("example2", "5")
                put("example3", "0")
                put("example4", "6")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("question", "3 + 1 = ?")
                put("answer", "4")
                put("example1", "8")
                put("example2", "3")
                put("example3", "4")
                put("example4", "0")
            }
        }
    )
    var problemNumber = 1
    var question: String? = ""
    var answer: String? = ""
    var example1: String? = ""
    var example2: String? = ""
    var example3: String? = ""
    var example4: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phoneme)

        //edit_readText = findViewById<View>(R.id.edit_readText) as EditText
        btn_ga = findViewById<View>(R.id.btn_ga) as Button
        btn_ga!!.isEnabled = false
        btn_ga!!.setOnClickListener(this)
        tts = TextToSpeech(this, this)

        btn_phoneme = findViewById(R.id.btn_phoneme)
        btn_phoneme2 = findViewById(R.id.btn_phoneme2)
        btn_phoneme3 = findViewById(R.id.btn_phoneme3)
        btn_phoneme4 = findViewById(R.id.btn_phoneme4)

        question = problems[problemNumber - 1]["question"] as String?
        answer = problems[problemNumber - 1]["answer"] as String?

        example1 = problems[problemNumber - 1]["btn_phoneme"] as String?
        example2 = problems[problemNumber - 1]["btn_phoneme2"] as String?
        example3 = problems[problemNumber - 1]["btn_phoneme3"] as String?
        example4 = problems[problemNumber - 1]["btn_phoneme4"] as String?

        btn_phoneme.setText(example1)
        btn_phoneme2.setText(example2)
        btn_phoneme3.setText(example3)
        btn_phoneme4.setText(example4)
    }

    // 글자 읽어주기
    private fun Speech() {
        //val text = btn_ga!!.text.toString().trim { it <= '가' }
        tts!!.speak("가",TextToSpeech.QUEUE_FLUSH,null)
        tts!!.setPitch(1.0.toFloat()) // 음량
        tts!!.setSpeechRate(1.0.toFloat()) // 재생속도
        //tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            // 작업 성공
            val language = tts!!.setLanguage(Locale.KOREAN) // 언어 설정
            if (language == TextToSpeech.LANG_MISSING_DATA
                || language == TextToSpeech.LANG_NOT_SUPPORTED
            ) {

                // 언어 데이터가 없거나, 지원하지 않는경우
                btn_ga!!.isEnabled = false
                Toast.makeText(this, "지원하지 않는 언어입니다.", Toast.LENGTH_SHORT).show()
            } else {

                // 준비 완료
                btn_ga!!.isEnabled = true
            }
        } else {

            // 작업 실패
            Toast.makeText(this, "TTS 작업에 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_ga -> Speech()
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
