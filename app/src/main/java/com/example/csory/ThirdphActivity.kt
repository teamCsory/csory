package com.example.csory

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_phoneme.*
import java.util.Random
import java.util.*




class ThirdphActivity : AppCompatActivity(), View.OnClickListener, TextToSpeech.OnInitListener {

    private var TAG = "phonemeActivity"
    private var btn_ga: Button? = null
    private var tts: TextToSpeech? = null
    private var btn_phoneme: Button? = null
    private var btn_phoneme2: Button? = null
    private var btn_phoneme3: Button? = null
    private var btn_phoneme4: Button? = null
    var soundtext = listOf("낙","학","작","닥","맛","닻","잣","밭","종","봉","공","동")
    var answer = listOf("낙","학","작","닥","맛","닻","잣","밭","종","봉","공","동")




    var problems = arrayOf<HashMap<*, *>>(
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "1 + 2 = ?")
                put("answer", "낙")
                put("example1", "낙")
                put("example2", "닥")
                put("example3", "학")
                put("example4", "작")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "3 + 2 = ?")
                put("answer", "학")
                put("example1", "낙")
                put("example2", "닥")
                put("example3", "학")
                put("example4", "작")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "3 + 3 = ?")
                put("answer", "작")
                put("example1", "낙")
                put("example2", "닥")
                put("example3", "학")
                put("example4", "작")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "0 + 3 = ?")
                put("answer", "닥")
                put("example1", "낙")
                put("example2", "닥")
                put("example3", "학")
                put("example4", "작")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "4 + 2 = ?")
                put("answer", "맛")
                put("example1", "밭")
                put("example2", "닻")
                put("example3", "잣")
                put("example4", "맛")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "5 + 4 = ?")
                put("answer", "닻")
                put("example1", "밭")
                put("example2", "닻")
                put("example3", "잣")
                put("example4", "맛")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "4 + 4 = ?")
                put("answer", "잣")
                put("example1", "밭")
                put("example2", "닻")
                put("example3", "잣")
                put("example4", "맛")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "2 + 5 = ?")
                put("answer", "밭")
                put("example1", "밭")
                put("example2", "닻")
                put("example3", "잣")
                put("example4", "맛")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "1 + 4 = ?")
                put("answer", "종")
                put("example1", "봉")
                put("example2", "종")
                put("example3", "공")
                put("example4", "동")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "1 + 4 = ?")
                put("answer", "봉")
                put("example1", "봉")
                put("example2", "종")
                put("example3", "공")
                put("example4", "동")
            }
        },object : HashMap<Any?, Any?>() {
            init {
                //put("question", "1 + 4 = ?")
                put("answer", "공")
                put("example1", "봉")
                put("example2", "종")
                put("example3", "공")
                put("example4", "동")
            }
        },
        object : HashMap<Any?, Any?>() {
            init {
                //put("question", "3 + 1 = ?")
                put("answer", "동")
                put("example1", "봉")
                put("example2", "종")
                put("example3", "공")
                put("example4", "동")
            }
        }
    )
    var problemNumber = 1
    //var question: String? = ""
    //var answer: String? = ""
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

        //edit_readText = findViewById<View>(R.id.edit_readText) as EditText
        btn_ga = findViewById<View>(R.id.btn_ga) as Button
        btn_ga!!.isEnabled = false
        btn_ga!!.setOnClickListener(this)
        tts = TextToSpeech(this, this)

        btn_phoneme = findViewById(R.id.btn_phoneme)
        btn_phoneme2 = findViewById(R.id.btn_phoneme2)
        btn_phoneme3 = findViewById(R.id.btn_phoneme3)
        btn_phoneme4 = findViewById(R.id.btn_phoneme4)

        //question = problems[problemNumber - 1]["question"] as String?
        //answer = problems[problemNumber - 1]["answer"] as String?

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

        //selectExample()



    }
    private fun showProblem() {
        //question = problems[problemNumber - 1]["question"] as String?
        //answer = problems[problemNumber - 1]["answer"] as String?
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
            //Log.d(TAG, example1!!)
            if (answer!![problemNumber-1].equals(example1)) {
                totalCorrect += 1
                //totalCorrectTextView!!.text = Integer.toString(totalCorrect)
                //correctIncorrectTextView!!.text = "Correct"
                Toast.makeText(this, R.string.answer_true, Toast.LENGTH_SHORT).show()
                //correct.setImageResource(R.drawable.correct)
                //correct.setVisibility(View.VISIBLE)
                anim.duration=400
                anim.fillAfter=false
                correct.animation=anim
                correct.visibility=View.VISIBLE
                correct.visibility=View.INVISIBLE
                //correct.bringToFront()

            }
            else {
                //correctIncorrectTextView!!.text = "Incorrect"
                Toast.makeText(this, R.string.answer_false, Toast.LENGTH_SHORT).show()
                anim2.duration=400
                anim2.fillAfter=false
                wrong.animation=anim2
                wrong.visibility=View.VISIBLE
                wrong.visibility=View.INVISIBLE
            }

            //correct.visibility=View.GONE
            //wrong.visibility=View.GONE
            problemNumber += 1
            //fadeout()

            showProblem()

        }

        btn_phoneme2?.setOnClickListener() {
            Log.d(TAG, "btn_phoneme2")
            example2?.let { Log.d(TAG, it) }
            val anim = TranslateAnimation(0f,correct.width.toFloat(),0f,0f)
            val anim2 = TranslateAnimation(0f,-wrong.width.toFloat(),0f,0f)
            if (answer!![problemNumber-1].equals(example2)) {
                totalCorrect += 1
                //totalCorrectTextView!!.text = Integer.toString(totalCorrect)
                //correctIncorrectTextView!!.text = "Correct"
                Toast.makeText(this, R.string.answer_true, Toast.LENGTH_SHORT).show()

                anim.duration=400
                anim.fillAfter=false
                correct.animation=anim
                correct.visibility=View.VISIBLE
                correct.visibility=View.INVISIBLE
            }
            else {
                //correctIncorrectTextView!!.text = "Incorrect"
                Toast.makeText(this, R.string.answer_false, Toast.LENGTH_SHORT).show()
                anim2.duration=400
                anim2.fillAfter=false
                wrong.animation=anim2
                wrong.visibility=View.VISIBLE
                wrong.visibility=View.INVISIBLE
            }

            problemNumber += 1
            showProblem()
        }
        btn_phoneme3?.setOnClickListener() {
            Log.d(TAG, "btn_phoneme3")
            example3?.let { Log.d(TAG, it) }
            val anim = TranslateAnimation(0f,correct.width.toFloat(),0f,0f)
            val anim2 = TranslateAnimation(0f,-wrong.width.toFloat(),0f,0f)
            if (answer!![problemNumber-1].equals(example3)) {
                totalCorrect += 1
                //totalCorrectTextView!!.text = Integer.toString(totalCorrect)
                //correctIncorrectTextView!!.text = "Correct"
                Toast.makeText(this, R.string.answer_true, Toast.LENGTH_SHORT).show()

                anim.duration=400
                anim.fillAfter=false
                correct.animation=anim
                correct.visibility=View.VISIBLE
                correct.visibility=View.INVISIBLE
            }
            else {
                //correctIncorrectTextView!!.text = "Incorrect"
                Toast.makeText(this, R.string.answer_false, Toast.LENGTH_SHORT).show()

                anim2.duration=400
                anim2.fillAfter=false
                wrong.animation=anim2
                wrong.visibility=View.VISIBLE
                wrong.visibility=View.INVISIBLE
            }

            problemNumber += 1
            showProblem()
        }
        btn_phoneme4?.setOnClickListener() {
            Log.d(TAG, "btn_phoneme4")
            example4?.let { Log.d(TAG, it) }
            val anim = TranslateAnimation(0f,correct.width.toFloat(),0f,0f)
            val anim2 = TranslateAnimation(0f,-wrong.width.toFloat(),0f,0f)
            if (answer!![problemNumber-1].equals(example4)) {
                totalCorrect += 1
                //totalCorrectTextView!!.text = Integer.toString(totalCorrect)
                //correctIncorrectTextView!!.text = "Correct"
                Toast.makeText(this, R.string.answer_true, Toast.LENGTH_SHORT).show()

                anim.duration=400
                anim.fillAfter=false
                correct.animation=anim
                correct.visibility=View.VISIBLE
                correct.visibility=View.INVISIBLE
            }
            else {
                //correctIncorrectTextView!!.text = "Incorrect"
                Toast.makeText(this, R.string.answer_false, Toast.LENGTH_SHORT).show()

                anim2.duration=400
                anim2.fillAfter=false
                wrong.animation=anim2
                wrong.visibility=View.VISIBLE
                wrong.visibility=View.INVISIBLE
            }

            problemNumber += 1
            showProblem()
        }




    }


    // 글자 읽어주기
    private fun Speech() {
        //val text = btn_ga!!.text.toString().trim { it <= '가' }
        tts!!.speak(soundtext[problemNumber-1],TextToSpeech.QUEUE_FLUSH,null)
        tts!!.setPitch(1.0.toFloat()) // 음량
        tts!!.setSpeechRate(0.5.toFloat()) // 재생속도

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