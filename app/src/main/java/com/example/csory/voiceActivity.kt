package com.example.csory

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.EditText
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


class voiceActivity : AppCompatActivity(), View.OnClickListener, TextToSpeech.OnInitListener {

    private var edit_readText: EditText? = null
    private var btn_speech: Button? = null
    private var tts: TextToSpeech? = null

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognitionListener: RecognitionListener



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice)
        getWindow().setWindowAnimations(0)

        edit_readText = findViewById<View>(R.id.edit_readText) as EditText
        btn_speech = findViewById<View>(R.id.btn_speech) as Button
        btn_speech!!.isEnabled = false
        btn_speech!!.setOnClickListener(this)
        tts = TextToSpeech(this, this)

        requestPermission()

        var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")

        setListener()

        btn_mike.setOnClickListener {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
            speechRecognizer.setRecognitionListener(recognitionListener)
            speechRecognizer.startListening(intent)
        }


    }


    private fun Speech() {
        val text = edit_readText!!.text.toString().trim { it <= ' ' }
        tts!!.setPitch(1.0.toFloat())
        tts!!.setSpeechRate(1.0.toFloat())
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {


            val language = tts!!.setLanguage(Locale.KOREAN)
            if (language == TextToSpeech.LANG_MISSING_DATA
                || language == TextToSpeech.LANG_NOT_SUPPORTED
            ) {


                btn_speech!!.isEnabled = false
                Toast.makeText(this, "???????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show()
            } else {


                btn_speech!!.isEnabled = true
            }
        } else {


            Toast.makeText(this, "TTS ????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
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

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)
        }
    }

    private fun setListener() {
        recognitionListener = object: RecognitionListener {

            override fun onReadyForSpeech(params: Bundle?) {
                Toast.makeText(applicationContext, "??????????????? ???????????????.", Toast.LENGTH_SHORT).show()
            }

            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {
                val message: String = when (error) {
                    SpeechRecognizer.ERROR_AUDIO -> "????????? ??????"
                    SpeechRecognizer.ERROR_CLIENT -> "??????????????? ??????"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "????????? ??????"
                    SpeechRecognizer.ERROR_NETWORK -> "???????????? ??????"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "????????? ????????????"
                    SpeechRecognizer.ERROR_NO_MATCH -> "?????? ???????????????"
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "????????? ????????? ???????????????"
                    SpeechRecognizer.ERROR_SERVER -> "????????? ?????????"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "????????? ????????????"
                    else -> "??? ??? ?????? ?????????"
                }
                Toast.makeText(applicationContext, "error : $message", Toast.LENGTH_SHORT).show()
            }

            override fun onResults(results: Bundle?) {

                val matches: ArrayList<String>? =
                    results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) as ArrayList<String>

                if (matches != null) {
                    for (i in 0 until matches.size) {
                        voice_text.text = matches[i]
                    }
                }

            }
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        }
    }
}

