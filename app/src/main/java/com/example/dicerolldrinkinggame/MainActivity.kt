package com.example.dicerolldrinkinggame

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import dice.Dice

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val diceRoller: ImageButton = findViewById(R.id.diceButton)
        val animation = AnimationUtils.loadAnimation(this,R.anim.jiggle)
        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.opus_remix)
        val restartButton : Button = findViewById(R.id.restart_button)
        // lag en timer for hva som skal skje når du har vunnet
        // link til brukt sang : https://www.youtube.com/watch?v=_lAsGw7evcw&ab_channel=AlexanderPeraltaArevalo
        val gameOver = object : CountDownTimer(126000,126000){
            override fun onTick(p0: Long) {

            }
            //on finish virker ikke som blir kallt
            override fun onFinish() {
                // her må du set visibility.
               diceRoller.setVisibility(View.GONE)
                println("yooo")
            }

        }

        val timer = object: CountDownTimer(350,350){
            override fun onTick(millisUntilFinished: Long) {
                diceRoller.startAnimation(animation)
            }
            override fun onFinish() {
                diceRoller.clearAnimation()
            }


        }
        restartButton.setOnClickListener(){
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                mediaPlayer.seekTo(0)
            }
        }
        diceRoller.setOnClickListener {
                mediaPlayer.start()
                mediaPlayer.setVolume(100f, 100f)
                timer.start()
                gameOver.start()

            val rolledDice: Int = Dice().rollDice()
            val drawableResource = when (rolledDice){
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> {
                    R.drawable.dice_6
                }
            }
            diceRoller.setImageResource(drawableResource)
            diceRoller.contentDescription = rolledDice.toString()

        }

    }
}

// https://stackoverflow.com/questions/7404995/android-switch-between-layouts
// se link for kordan du bytte layout

// https://www.youtube.com/watch?v=2xyNawgIk7Q&ab_channel=ProgrammerWorld
// se for hvordan du kan ha flere layouts

// https://www.youtube.com/watch?v=I2cdYnCyoDk&ab_channel=CodingDemos
// flere animasjoner.

//https://developer.android.com/guide/fragments
