package com.example.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        val questionAdd = findViewById<EditText>(R.id.enter_question)
        val answerAdd = findViewById<EditText>(R.id.enter_answer)

        val saveButton = findViewById<ImageView>(R.id.save_card_button)
        saveButton.setOnClickListener {
            val questionString = questionAdd.text.toString()
            val answerString = answerAdd.text.toString()

            val data = Intent()
            data.putExtra("QUESTION_KEY", questionString)
            data.putExtra("ANSWER_KEY", answerString)

            setResult(RESULT_OK, data)
            finish()
        }

            val cancelAddFlashcard = findViewById<ImageView>(R.id.cancel_card_button)
            cancelAddFlashcard.setOnClickListener {
                finish()
            }

        }

    }
