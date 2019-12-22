package com.example.sudoku

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    private var svc:Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_play.setOnClickListener(){
            startActivity(Intent(applicationContext, LevelActivity::class.java))
        }

        imageButton_exit.setOnClickListener(){
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Внимание")
                .setMessage("Вы уверены, что хотите выйти из приложения?")
                .setPositiveButton("Да, уверен", DialogInterface.OnClickListener { dialog, which ->
                    exitProcess(0)
                })
                .setNegativeButton("Нет", DialogInterface.OnClickListener{dialog, which ->
                    dialog.cancel()})
            val alert = builder.create()
            alert.show()
        }

        imageButton_info.setOnClickListener(){
            startActivity(Intent(applicationContext, InfoActivity::class.java))
        }

        imageButton_settings.setOnClickListener(){
            startActivity(Intent(applicationContext, SettingsActivity::class.java))
        }

        svc = Intent(this, SoundService::class.java)
        startService(svc)
    }

    override fun onStart() {
        super.onStart()
        val change = Change(this)
        change.changeTheme(this, this.findViewById(R.id.layout_main))

    }

}
