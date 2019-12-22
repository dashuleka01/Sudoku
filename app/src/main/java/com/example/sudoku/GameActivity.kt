 package com.example.sudoku

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*

 class GameActivity : AppCompatActivity() {

     var level = 0
     private val maxLevel = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        level = intent.getIntExtra("level", 0)
        val change = Change(this)

        var game = Game(level)
        val firstlist = game.getGame().toMutableList()
        var list = game.getGame().toMutableList()
        var adapter = DataAdapter(this, list, firstlist)


        field.adapter = adapter
        field.numColumns = 9
        field.setOnItemClickListener(){ _, _, i, _ ->
            if (firstlist[i] == 0) {
                for (j in 0 until radioGroupNumber.childCount) {
                    if((radioGroupNumber.getChildAt(j) as RadioButton).isChecked)
                        list[i] = j + 1
                }
                field.adapter = DataAdapter(this, list, firstlist)
            }
        }

        imageButton_back3.setOnClickListener(){
            finish()
        }

        imageButton_settings.setOnClickListener(){
            startActivity(Intent(applicationContext, SettingsActivity::class.java))
        }

        button_check.setOnClickListener(){
            for (i in 0..80){
                list[i] = adapter.getItem(i)
            }
            val res = game.check(list)

            val builder = AlertDialog.Builder(this@GameActivity)
            if (res) {
                builder.setTitle("Поздравляем!")
                    .setMessage("Вы выиграли! ")
                    .setPositiveButton("Назад в меню") { _, _ -> finish() }
                    if(level < maxLevel){
                        builder.setNegativeButton(
                            "Следующий уровень"
                        ) { dialog, _ ->
                            val intent = Intent(applicationContext, GameActivity::class.java)
                            intent.putExtra("level", level + 1)
                            startActivity(intent)
                            finish()
                        }
                    }
            }
            else{
                builder.setTitle("Неправильно")
                    .setMessage("Начать заново? ")
                    .setPositiveButton(
                        "Да"
                    ) { _, _ -> change.restartAct(this, level)  }
                    .setNegativeButton(
                        "Продолжить"
                    ) { dialog, _ ->
                        dialog.cancel()
                    }
            }
            val alert = builder.create()
            alert.show()
        }

        button_again.setOnClickListener(){
            change.restartAct(this, level)
        }
    }

     override fun onStart() {
         super.onStart()
         val change = Change(this)
         change.changeTheme(this, this.findViewById(R.id.layout_game), level)
     }



}
