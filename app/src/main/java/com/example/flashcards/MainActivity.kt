package com.example.flashcards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        val answerOption1 = findViewById<TextView>(R.id.answer_option1)
        val answerOption2 = findViewById<TextView>(R.id.answer_option2)
        val answerOption3 = findViewById<TextView>(R.id.answer_option3)


        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE
            answerOption1.visibility = View.INVISIBLE
            answerOption2.visibility = View.INVISIBLE
            answerOption3.visibility = View.INVISIBLE

        }
        flashcardAnswer.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            flashcardAnswer.visibility = View.INVISIBLE
            answerOption1.visibility = View.VISIBLE
            answerOption2.visibility = View.VISIBLE
            answerOption3.visibility = View.VISIBLE

        }
    }
}




