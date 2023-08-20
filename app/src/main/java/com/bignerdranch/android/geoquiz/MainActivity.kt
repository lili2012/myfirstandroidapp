package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private lateinit var trueButton: Button
    //private lateinit var falseButton: Button
    private lateinit var binding: ActivityMainBinding
    private var currentIndex = 0
    private fun setQuestion(){
        var questionTextResId = questions[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean){
        var correctAnswer = questions[currentIndex].answer
        var isAnswerCorrect = (correctAnswer == userAnswer)
        var messageResId = if(isAnswerCorrect){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //trueButton = findViewById(R.id.true_button)
        //falseButton = findViewById(R.id.false_button)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.trueButton.setOnClickListener{ view: View->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener{ view: View ->
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener{
            currentIndex = (currentIndex+1)% questions.size
            setQuestion()
        }
        setQuestion()
    }

    private val questions = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

}