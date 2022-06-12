package screens

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.dicerolldrinkinggame.R
import dice.Dice


class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val diceRoller: ImageButton = findViewById(R.id.diceButton)
        val animation = AnimationUtils.loadAnimation(this, R.anim.jiggle)
        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.opus_remix)
        // lag en timer for hva som skal skje når du har vunnet
        // link til brukt sang : https://www.youtube.com/watch?v=_lAsGw7evcw&ab_channel=AlexanderPeraltaArevalo
        val timer = object: CountDownTimer(350,350){
            override fun onTick(millisUntilFinished: Long) {
                diceRoller.startAnimation(animation)
            }
            override fun onFinish() {
                diceRoller.clearAnimation()
            }

        }

        diceRoller.setOnClickListener {
            mediaPlayer.start()
            mediaPlayer.setVolume(100f,100f)
            timer.start()
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