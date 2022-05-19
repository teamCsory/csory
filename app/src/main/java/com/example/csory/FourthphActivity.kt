package com.example.csory

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_phoneme.*
import java.util.Random
import java.util.*




class FourthphActivity : AppCompatActivity(), View.OnClickListener, TextToSpeech.OnInitListener {

    private var TAG = "phonemeActivity"
    private var btn_ga: Button? = null
    private var tts: TextToSpeech? = null
    private var btn_phoneme: Button? = null
    private var btn_phoneme2: Button? = null
    private var btn_phoneme3: Button? = null
    private var btn_phoneme4: Button? = null
    var soundtext = listOf("단","싼","잔","산","쎔","잼","샘","댐","선","전","썬","언")
    var answer = listOf("단","싼","잔","산","쎔","잼","샘","댐","선","전","썬","언")




    var problems = arrayOf<HashMap<*, *>>(
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "으")
                put("example1", "잔")
                put("example2", "산")
                put("example3", "싼")
                put("example4", "단")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "즈")
                put("example1", "잔")
                put("example2", "산")
                put("example3", "싼")
                put("example4", "단")
            }

        },
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "쓰")
                put("example1", "잔")
                put("example2", "산")
                put("example3", "싼")
                put("example4", "단")

            }
        },
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "스")
                put("example1", "잔")
                put("example2", "산")
                put("example3", "싼")
                put("example4", "단")
            }

        },
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "짱")
                put("example1", "샘")
                put("example2", "쎔")
                put("example3", "댐")
                put("example4", "잼")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "쌍")
                put("example1", "샘")
                put("example2", "쎔")
                put("example3", "댐")
                put("example4", "잼")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "팡")
                put("example1", "샘")
                put("example2", "쎔")
                put("example3", "댐")
                put("example4", "잼")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "상")
                put("example1", "샘")
                put("example2", "쎔")
                put("example3", "댐")
                put("example4", "잼")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "쫑")
                put("example1", "선")
                put("example2", "언")
                put("example3", "전")
                put("example4", "썬")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "종")
                put("example1", "선")
                put("example2", "언")
                put("example3", "전")
                put("example4", "썬")
            }
        },object : HashMap<Any?, Any?>() {
            init {

                put("answer", "송")
                put("example1", "선")
                put("example2", "언")
                put("example3", "전")
                put("example4", "썬")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {

                put("answer", "쏭")
                put("example1", "선")
                put("example2", "언")
                put("example3", "전")
                put("example4", "썬")
            }
        }
    )
    var problemNumber = 1
    var example1: String? = ""
    var example2: String? = ""
    var example3: String? = ""
    var example4: String? = ""
    var totalCorrect = 0
    var totalCorrectTextView: TextView? = null
    var correctIncorrectTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phoneme)

        btn_ga = findViewById<View>(R.id.btn_ga) as Button
        btn_ga!!.isEnabled = false
        btn_ga!!.setOnClickListener(this)
        tts = TextToSpeech(this, this)

        btn_phoneme = findViewById(R.id.btn_phoneme)
        btn_phoneme2 = findViewById(R.id.btn_phoneme2)
        btn_phoneme3 = findViewById(R.id.btn_phoneme3)
        btn_phoneme4 = findViewById(R.id.btn_phoneme4)


        example1 = problems[problemNumber - 1]["btn_phoneme"] as String?
        example2 = problems[problemNumber - 1]["btn_phoneme2"] as String?
        example3 = problems[problemNumber - 1]["btn_phoneme3"] as String?
        example4 = problems[problemNumber - 1]["btn_phoneme4"] as String?

        btn_phoneme?.setText(example1)
        btn_phoneme2?.setText(example2)
        btn_phoneme3?.setText(example3)
        btn_phoneme4?.setText(example4)

        showProblem()
        setButton()

        totalCorrectTextView?.setText("Total Correct: 0")
        correctIncorrectTextView?.setText("Correct/Incorrect")




    }
    private fun showProblem() {

        example1 = problems[problemNumber - 1]["example1"] as String?
        example2 = problems[problemNumber - 1]["example2"] as String?
        example3 = problems[problemNumber - 1]["example3"] as String?
        example4 = problems[problemNumber - 1]["example4"] as String?

        btn_phoneme?.setText(example1)
        btn_phoneme2?.setText(example2)
        btn_phoneme3?.setText(example3)
        btn_phoneme4?.setText(example4)


    }



    private fun setButton() {

        btn_phoneme?.setOnClickListener() {
            Log.d(TAG, "btn_phoneme")
            example1?.let { Log.d(TAG, it) }
            val anim = TranslateAnimation(0f,correct.width.toFloat(),0f,0f)
            val anim2 = TranslateAnimation(0f,-wrong.width.toFloat(),0f,0f)

            if (answer!![problemNumber-1].equals(example1)) {
                totalCorrect += 1

                anim.duration=400
                anim.fillAfter=false
                correct.animation=anim
                correct.visibility=View.VISIBLE
                correct.visibility=View.INVISIBLE

                if(problemNumber == 3){
                    var tst25 = layoutInflater.inflate(R.layout.custom_toast, null)
                    //tst30.setBackgroundResource(android.R.drawable.toast_frame)
                    var tst25_1 = Toast(this)
                    tst25_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst25_1.duration=Toast.LENGTH_SHORT
                    tst25_1.view = tst25
                    tst25_1.show()
                }
                if(problemNumber == 6){
                    var tst50 = layoutInflater.inflate(R.layout.custom_toast50, null)
                    tst50.setBackgroundResource(android.R.drawable.toast_frame)
                    var tst50_1 = Toast(this)
                    tst50_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst50_1.duration=Toast.LENGTH_SHORT
                    tst50_1.view = tst50
                    tst50_1.show()
                }

            }
            else {

                anim2.duration=400
                anim2.fillAfter=false
                wrong.animation=anim2
                wrong.visibility=View.VISIBLE
                wrong.visibility=View.INVISIBLE
                if(problemNumber == 3){
                    var tst25 = layoutInflater.inflate(R.layout.custom_toast, null)
                    var tst25_1 = Toast(this)
                    tst25_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst25_1.duration=Toast.LENGTH_SHORT
                    tst25_1.view = tst25
                    tst25_1.show()
                }
                if(problemNumber == 6){
                    var tst50 = layoutInflater.inflate(R.layout.custom_toast50, null)
                    tst50.setBackgroundResource(android.R.drawable.toast_frame)
                    var tst50_1 = Toast(this)
                    tst50_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst50_1.duration=Toast.LENGTH_SHORT
                    tst50_1.view = tst50
                    tst50_1.show()
                }
            }


            problemNumber += 1

            if(problemNumber<13){
                showProblem()
            }
            else{
                val myIntent = Intent(this, ResultActivity::class.java)
                val bye:String=totalCorrect.toString()
                myIntent.putExtra("keykey", bye)
                startActivity(myIntent)
            }

        }

        btn_phoneme2?.setOnClickListener() {
            Log.d(TAG, "btn_phoneme2")
            example2?.let { Log.d(TAG, it) }
            val anim = TranslateAnimation(0f,correct.width.toFloat(),0f,0f)
            val anim2 = TranslateAnimation(0f,-wrong.width.toFloat(),0f,0f)
            if (answer!![problemNumber-1].equals(example2)) {
                totalCorrect += 1

                anim.duration=400
                anim.fillAfter=false
                correct.animation=anim
                correct.visibility=View.VISIBLE
                correct.visibility=View.INVISIBLE

                if(problemNumber == 3){
                    var tst25 = layoutInflater.inflate(R.layout.custom_toast, null)
                    //tst30.setBackgroundResource(android.R.drawable.toast_frame)
                    var tst25_1 = Toast(this)
                    tst25_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst25_1.duration=Toast.LENGTH_SHORT
                    tst25_1.view = tst25
                    tst25_1.show()
                }
                if(problemNumber == 6){
                    var tst50 = layoutInflater.inflate(R.layout.custom_toast50, null)
                    tst50.setBackgroundResource(android.R.drawable.toast_frame)
                    var tst50_1 = Toast(this)
                    tst50_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst50_1.duration=Toast.LENGTH_SHORT
                    tst50_1.view = tst50
                    tst50_1.show()
                }
            }
            else {

                anim2.duration=400
                anim2.fillAfter=false
                wrong.animation=anim2
                wrong.visibility=View.VISIBLE
                wrong.visibility=View.INVISIBLE

                if(problemNumber == 3){
                    var tst25 = layoutInflater.inflate(R.layout.custom_toast, null)

                    var tst25_1 = Toast(this)
                    tst25_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst25_1.duration=Toast.LENGTH_SHORT
                    tst25_1.view = tst25
                    tst25_1.show()
                }
                if(problemNumber == 6){
                    var tst50 = layoutInflater.inflate(R.layout.custom_toast50, null)
                    tst50.setBackgroundResource(android.R.drawable.toast_frame)
                    var tst50_1 = Toast(this)
                    tst50_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst50_1.duration=Toast.LENGTH_SHORT
                    tst50_1.view = tst50
                    tst50_1.show()
                }
            }

            problemNumber += 1
            if(problemNumber<13){
                showProblem()
            }
            else{
                val myIntent = Intent(this, ResultActivity::class.java)
                val bye:String=totalCorrect.toString()
                myIntent.putExtra("keykey", bye)
                startActivity(myIntent)
            }
        }
        btn_phoneme3?.setOnClickListener() {
            Log.d(TAG, "btn_phoneme3")
            example3?.let { Log.d(TAG, it) }
            val anim = TranslateAnimation(0f,correct.width.toFloat(),0f,0f)
            val anim2 = TranslateAnimation(0f,-wrong.width.toFloat(),0f,0f)
            if (answer!![problemNumber-1].equals(example3)) {
                totalCorrect += 1

                anim.duration=400
                anim.fillAfter=false
                correct.animation=anim
                correct.visibility=View.VISIBLE
                correct.visibility=View.INVISIBLE

                if(problemNumber == 3){
                    var tst25 = layoutInflater.inflate(R.layout.custom_toast, null)

                    var tst25_1 = Toast(this)
                    tst25_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst25_1.duration=Toast.LENGTH_SHORT
                    tst25_1.view = tst25
                    tst25_1.show()
                }
                if(problemNumber == 6){
                    var tst50 = layoutInflater.inflate(R.layout.custom_toast50, null)
                    tst50.setBackgroundResource(android.R.drawable.toast_frame)
                    var tst50_1 = Toast(this)
                    tst50_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst50_1.duration=Toast.LENGTH_SHORT
                    tst50_1.view = tst50
                    tst50_1.show()
                }
            }
            else {

                anim2.duration=400
                anim2.fillAfter=false
                wrong.animation=anim2
                wrong.visibility=View.VISIBLE
                wrong.visibility=View.INVISIBLE

                if(problemNumber == 3){
                    var tst25 = layoutInflater.inflate(R.layout.custom_toast, null)

                    var tst25_1 = Toast(this)
                    tst25_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst25_1.duration=Toast.LENGTH_SHORT
                    tst25_1.view = tst25
                    tst25_1.show()
                }
                if(problemNumber == 6){
                    var tst50 = layoutInflater.inflate(R.layout.custom_toast50, null)
                    tst50.setBackgroundResource(android.R.drawable.toast_frame)
                    var tst50_1 = Toast(this)
                    tst50_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst50_1.duration=Toast.LENGTH_SHORT
                    tst50_1.view = tst50
                    tst50_1.show()
                }
            }

            problemNumber += 1
            if(problemNumber<13){
                showProblem()
            }
            else{
                val myIntent = Intent(this, ResultActivity::class.java)
                val bye:String=totalCorrect.toString()
                myIntent.putExtra("keykey", bye)
                startActivity(myIntent)
            }
        }
        btn_phoneme4?.setOnClickListener() {
            Log.d(TAG, "btn_phoneme4")
            example4?.let { Log.d(TAG, it) }
            val anim = TranslateAnimation(0f,correct.width.toFloat(),0f,0f)
            val anim2 = TranslateAnimation(0f,-wrong.width.toFloat(),0f,0f)
            if (answer!![problemNumber-1].equals(example4)) {
                totalCorrect += 1

                anim.duration=400
                anim.fillAfter=false
                correct.animation=anim
                correct.visibility=View.VISIBLE
                correct.visibility=View.INVISIBLE

                if(problemNumber == 3){
                    var tst25 = layoutInflater.inflate(R.layout.custom_toast, null)

                    var tst25_1 = Toast(this)
                    tst25_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst25_1.duration=Toast.LENGTH_SHORT
                    tst25_1.view = tst25
                    tst25_1.show()
                }
                if(problemNumber == 6){
                    var tst50 = layoutInflater.inflate(R.layout.custom_toast50, null)
                    tst50.setBackgroundResource(android.R.drawable.toast_frame)
                    var tst50_1 = Toast(this)
                    tst50_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst50_1.duration=Toast.LENGTH_SHORT
                    tst50_1.view = tst50
                    tst50_1.show()
                }
            }
            else {

                anim2.duration=400
                anim2.fillAfter=false
                wrong.animation=anim2
                wrong.visibility=View.VISIBLE
                wrong.visibility=View.INVISIBLE

                if(problemNumber == 3){
                    var tst25 = layoutInflater.inflate(R.layout.custom_toast, null)

                    var tst25_1 = Toast(this)
                    tst25_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst25_1.duration=Toast.LENGTH_SHORT
                    tst25_1.view = tst25
                    tst25_1.show()
                }
                if(problemNumber == 6){
                    var tst50 = layoutInflater.inflate(R.layout.custom_toast50, null)
                    tst50.setBackgroundResource(android.R.drawable.toast_frame)
                    var tst50_1 = Toast(this)
                    tst50_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst50_1.duration=Toast.LENGTH_SHORT
                    tst50_1.view = tst50
                    tst50_1.show()
                }
            }

            problemNumber += 1
            if(problemNumber<13){
                showProblem()
            }
            else{
                val myIntent = Intent(this, ResultActivity::class.java)
                val bye:String=totalCorrect.toString()
                myIntent.putExtra("keykey", bye)
                startActivity(myIntent)
            }
        }




    }



    private fun Speech() {

        tts!!.speak(soundtext[problemNumber-1],TextToSpeech.QUEUE_FLUSH,null)
        tts!!.setPitch(1.0.toFloat())
        tts!!.setSpeechRate(0.4.toFloat())


    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {


            val language = tts!!.setLanguage(Locale.KOREAN)
            if (language == TextToSpeech.LANG_MISSING_DATA
                || language == TextToSpeech.LANG_NOT_SUPPORTED
            ) {


                btn_ga!!.isEnabled = false
                Toast.makeText(this, "지원하지 않는 언어입니다.", Toast.LENGTH_SHORT).show()
            } else {


                btn_ga!!.isEnabled = true
            }
        } else {


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