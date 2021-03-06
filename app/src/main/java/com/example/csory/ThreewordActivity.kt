package com.example.csory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.HashMap
import android.util.Log
import android.view.Gravity
import android.view.animation.TranslateAnimation
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_phoneme.*
import java.util.Random
import java.util.*

class ThreewordActivity : AppCompatActivity(), View.OnClickListener, TextToSpeech.OnInitListener {

    private var TAG = "phonemeActivity"
    private var btn_ga: Button? = null
    private var tts: TextToSpeech? = null
    private var btn_word1: Button? = null
    private var btn_word2: Button? = null

    var soundtext = listOf("장난감", "장난함", "길찾기", "길잡이", "테이프", "데이트", "동아리", "병아리", "밑그림", "빛그림")
    var answer = listOf("장난감", "장난함", "길찾기", "길잡이", "테이프", "데이트", "동아리", "병아리", "밑그림", "빛그림")




    var problems = arrayOf<HashMap<*, *>>(
        object : HashMap<Any?, Any?>() {
            init {
                put("answer", "몸")
                put("example1", "장난감")
                put("example2", "장난함")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("answer", "놈")
                put("example1", "장난감")
                put("example2", "장난함")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("answer", "돔")
                put("example1", "길찾기")
                put("example2", "길잡이")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("answer", "롬")
                put("example1", "길찾기")
                put("example2", "길잡이")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("answer", "6")
                put("example1", "테이프")
                put("example2", "데이트")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("answer", "9")
                put("example1", "테이프")
                put("example2", "데이트")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("answer", "8")
                put("example1", "동아리")
                put("example2", "병아리")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("answer", "7")
                put("example1", "동아리")
                put("example2", "병아리")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("answer", "5")
                put("example1", "밑그림")
                put("example2", "빛그림")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                put("answer", "4")
                put("example1", "밑그림")
                put("example2", "빛그림")
            }
        }
    )
    var problemNumber = 1
    var example1: String? = ""
    var example2: String? = ""

    var totalCorrect = 0
    var totalCorrectTextView: TextView? = null
    var correctIncorrectTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_threeword)

        btn_ga = findViewById<View>(R.id.btn_ga) as Button
        btn_ga!!.isEnabled = false
        btn_ga!!.setOnClickListener(this)
        tts = TextToSpeech(this, this)

        btn_word1 = findViewById(R.id.btn_word1)
        btn_word2 = findViewById(R.id.btn_word2)

        example1 = problems[problemNumber - 1]["btn_word1"] as String?
        example2 = problems[problemNumber - 1]["btn_word2"] as String?


        btn_word1?.setText(example1)
        btn_word2?.setText(example2)

        showProblem()
        setButton()

        totalCorrectTextView?.setText("Total Correct: 0")
        correctIncorrectTextView?.setText("Correct/Incorrect")

    }

    private fun showProblem() {
        example1 = problems[problemNumber - 1]["example1"] as String?
        example2 = problems[problemNumber - 1]["example2"] as String?

        btn_word1?.setText(example1)
        btn_word2?.setText(example2)


    }



    private fun setButton() {

        btn_word1?.setOnClickListener() {
            Log.d(TAG, "btn_phoneme")
            example1?.let { Log.d(TAG, it) }
            val anim = TranslateAnimation(0f,correct.width.toFloat(),0f,0f)
            val anim2 = TranslateAnimation(0f,-wrong.width.toFloat(),0f,0f)
            //Log.d(TAG, example1!!)
            if (answer!![problemNumber-1].equals(example1)) {
                totalCorrect += 1
                anim.duration=400
                anim.fillAfter=false
                correct.animation=anim
                correct.visibility=View.VISIBLE
                correct.visibility=View.INVISIBLE

                if(problemNumber == 3){
                    var tst30 = layoutInflater.inflate(R.layout.custom_toast30, null)
                    var tst30_1 = Toast(this)
                    tst30_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst30_1.duration=Toast.LENGTH_SHORT
                    tst30_1.view = tst30
                    tst30_1.show()
                }
                if(problemNumber == 5){
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
                    var tst30 = layoutInflater.inflate(R.layout.custom_toast30, null)
                    var tst30_1 = Toast(this)
                    tst30_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst30_1.duration=Toast.LENGTH_SHORT
                    tst30_1.view = tst30
                    tst30_1.show()
                }
                if(problemNumber == 5){
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

            if(problemNumber<11){
                showProblem()
            }
            else{
                val myIntent = Intent(this, ResultwordActivity::class.java)
                val bye:String=totalCorrect.toString()
                myIntent.putExtra("keykey", bye)
                startActivity(myIntent)
            }

        }

        btn_word2?.setOnClickListener() {
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
                    var tst30 = layoutInflater.inflate(R.layout.custom_toast30, null)
                    var tst30_1 = Toast(this)
                    tst30_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst30_1.duration=Toast.LENGTH_SHORT
                    tst30_1.view = tst30
                    tst30_1.show()
                }
                if(problemNumber == 5){
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
                    var tst30 = layoutInflater.inflate(R.layout.custom_toast30, null)
                    var tst30_1 = Toast(this)
                    tst30_1.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
                    tst30_1.duration=Toast.LENGTH_SHORT
                    tst30_1.view = tst30
                    tst30_1.show()
                }
                if(problemNumber == 5){
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
            if(problemNumber<11){
                showProblem()
            }
            else{
                val myIntent = Intent(this, ResultwordActivity::class.java)
                val bye:String=totalCorrect.toString()
                myIntent.putExtra("keykey", bye)
                startActivity(myIntent)
            }
        }

    }


    // 글자 읽어주기
    private fun Speech() {
        tts!!.speak(soundtext[problemNumber-1],TextToSpeech.QUEUE_FLUSH,null)
        tts!!.setPitch(1.0.toFloat()) // 음량
        tts!!.setSpeechRate(0.5.toFloat()) // 재생속도

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