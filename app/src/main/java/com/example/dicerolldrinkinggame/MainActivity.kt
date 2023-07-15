package com.example.dicerolldrinkinggame

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import dice.Dice

class MainActivity : AppCompatActivity() {

    private lateinit var diceRoller: ImageButton
    private lateinit var restartButton: Button
    private lateinit var loseDescription: TextView
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var animation: Animation
    private var runningGameOverCounter = false

    private val gameOverTimer = object : CountDownTimer(122600,122600) {
        override fun onTick(p0: Long) {}
        override fun onFinish() {
            showGameOver()
        }
    }

    private val rollDiceTimer = object: CountDownTimer(350,350) {
        override fun onTick(millisUntilFinished: Long) {
            diceRoller.startAnimation(animation)
        }
        override fun onFinish() {
            diceRoller.clearAnimation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        diceRoller = findViewById(R.id.diceButton)
        restartButton = findViewById(R.id.restart_button)
        loseDescription = findViewById(R.id.lose_description)
        mediaPlayer = MediaPlayer.create(this, R.raw.opus_remix)
        animation = AnimationUtils.loadAnimation(this, R.anim.jiggle)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        diceRoller.setOnClickListener {
            rollDice()
        }
        restartButton.setOnClickListener {
            restartGame()
        }
    }

    private fun rollDice() {
        if(!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            mediaPlayer.setVolume(100f, 100f)
        }
        rollDiceTimer.start()
        if(!runningGameOverCounter){
            gameOverTimer.start()
            runningGameOverCounter = true
        }
        val rolledDice: Int = Dice().rollDice()
        val drawableResource = getDiceDrawableResource(rolledDice)
        diceRoller.setImageResource(drawableResource)
        diceRoller.contentDescription = rolledDice.toString()
    }

    private fun getDiceDrawableResource(diceNumber: Int) = when (diceNumber) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    private fun restartGame() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
        runningGameOverCounter = false
        gameOverTimer.cancel()
        if(!diceRoller.isVisible)
            diceRoller.setVisibility(View.VISIBLE)
        if(loseDescription.isVisible)
            loseDescription.setVisibility(View.GONE)
    }

    private fun showGameOver() {
        diceRoller.setVisibility(View.GONE)
        loseDescription.setVisibility(View.VISIBLE)
    }
}
