package com.example.sudoku

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_level.*

class LevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        imageButton_back2.setOnClickListener(){
            finish()
        }

        val intent  = Intent(applicationContext, GameActivity::class.java)
        button_level1.setOnClickListener(){
            intent.putExtra("level", 1)
            startActivity(intent)
        }

        button_level2.setOnClickListener(){
            intent.putExtra("level", 2)
            startActivity(intent)
        }

        button_level3.setOnClickListener(){
            intent.putExtra("level", 3)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val change = Change(this)
        change.changeTheme(this, this.layout_level)

    }
}
