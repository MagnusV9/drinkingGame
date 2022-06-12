package com.example.dicerolldrinkinggame


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import screens.GameActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
        //val allScreens = mapOf("gameScreen" to R.layout.game_screen, "homeScreen" to R.layout.home_screen, "winScreen" to R.layout.win_screen)
        println(R.id.play)
        val playButton : Button = findViewById(R.id.play)
        val howToPlayButton : Button = findViewById(R.id.howToPlay)
        val playText :TextView = findViewById(R.id.play_description)
        val menueButton : Button = findViewById(R.id.menue)
        playButton.setOnClickListener(){
            val game = GameActivity()
            setContentView(R.layout.activity_game)
        }

    }
    private fun switchActivities() {
        val switchActivityIntent = Intent(this, GameActivity::class)
        startActivity(switchActivityIntent)
    }
}

// kræsjer litt usikker på hvorfor.

// https://stackoverflow.com/questions/7404995/android-switch-between-layouts
// se link for kordan du bytte layout

// https://www.youtube.com/watch?v=2xyNawgIk7Q&ab_channel=ProgrammerWorld
// se for hvordan du kan ha flere layouts

// https://www.youtube.com/watch?v=I2cdYnCyoDk&ab_channel=CodingDemos
// flere animasjoner.

//https://developer.android.com/guide/fragments
