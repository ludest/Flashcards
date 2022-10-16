package com.example.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()

    var currCardDisplayedIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()

        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)

        if (allFlashcards.size > 0) {
            flashcardQuestion.text = allFlashcards[0].question
            flashcardAnswer.text = allFlashcards[0].answer
        }

        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE
        }

        flashcardAnswer.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            flashcardAnswer.visibility = View.INVISIBLE
        }

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    result ->

                val data: Intent? = result.data

                if (data != null) {
                    val questionString = data.getStringExtra("QUESTION_KEY")
                    val answerString = data.getStringExtra("ANSWER_KEY")

                    flashcardQuestion.text = questionString
                    flashcardAnswer.text = answerString

                    if (!questionString.isNullOrEmpty() && !answerString.isNullOrEmpty()) {
                        flashcardDatabase.insertCard(Flashcard(questionString, answerString))
                        allFlashcards = flashcardDatabase.getAllCards().toMutableList()
                    }
                }
            }
        val addFlashcard = findViewById<ImageView>(R.id.add_card)
        addFlashcard.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)
        }
        val nextButton = findViewById<ImageView>(R.id.next_card)
        nextButton.setOnClickListener {
            if (allFlashcards.isEmpty()) {
                return@setOnClickListener
            }
            currCardDisplayedIndex++

            if (currCardDisplayedIndex >= allFlashcards.size) {
                currCardDisplayedIndex = 0
            }
            allFlashcards = flashcardDatabase.getAllCards().toMutableList()

            val question = allFlashcards[currCardDisplayedIndex].question
            val answer = allFlashcards[currCardDisplayedIndex].answer

            flashcardQuestion.text = question
            flashcardAnswer.text = answer
        }

        findViewById<View>(R.id.delete_card).setOnClickListener {
            val flashcardQuestionToDelete = findViewById<TextView>(R.id.flashcard_question).text.toString()
            flashcardDatabase.deleteCard(flashcardQuestionToDelete)

            allFlashcards = flashcardDatabase.getAllCards().toMutableList()
        }
    }
}



