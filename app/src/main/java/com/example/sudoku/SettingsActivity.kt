package com.example.sudoku

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioManager
import android.os.Bundle
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.*
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : AppCompatActivity() {
    private var dayTheme = true
    private var autoTheme = false
    private var sound = 0
    var audioManager: AudioManager? = null

    companion object {
        val THEME = "theme"
        val AUTOTHEME = "auto"
        val SOUND = "sound"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        val change = Change(this)
        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        seekBar_sound.max = audioManager!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

        imageButton_back.setOnClickListener(){
            finish()
        }

        radioButton_day.setOnClickListener(){
            setDefaultNightMode(MODE_NIGHT_NO)
            change.restartAct(this)
        }

        radioButton_night.setOnClickListener(){
            setDefaultNightMode(MODE_NIGHT_YES)
            change.restartAct(this)
        }

        seekBar_sound.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                audioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }


    override fun onStart() {
        super.onStart()
        loadSettings()
        val change = Change(this)
        change.changeTheme(this, this.layout_settings)
    }

    override fun onStop() {
        super.onStop()
        saveSettings()
    }

    private fun saveSettings(){
        val sPref= getPreferences(Context.MODE_PRIVATE)
        val ed: SharedPreferences.Editor=sPref.edit()
        ed.putBoolean(THEME, isDay())
        ed.putInt(SOUND, seekBarStatus())
        ed.commit()
    }

    private fun loadSettings(){
        val sPref= getPreferences(Context.MODE_PRIVATE)
        dayTheme = sPref.getBoolean(THEME, true)
        autoTheme = sPref.getBoolean(AUTOTHEME, false)
        sound = sPref.getInt(SOUND, 0)
        if (getDefaultNightMode() == MODE_NIGHT_NO){
            findViewById<RadioButton>(R.id.radioButton_day).isChecked = true
        }
        else{
            findViewById<RadioButton>(R.id.radioButton_night).isChecked = true
        }
        if(autoTheme){
            blockRadio(false)
        }
        findViewById<SeekBar>(R.id.seekBar_sound).progress = audioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC)

    }

    private fun isDay(): Boolean{
        val selectedRadioButton = findViewById<RadioButton>(R.id.radioButton_day)
        return selectedRadioButton.isChecked
    }

    private fun seekBarStatus(): Int {
        val seek = findViewById<SeekBar>(R.id.seekBar_sound)
        return seek.progress
    }


    private fun blockRadio(en: Boolean){
        for (i in 0 until radioGroup_theme.childCount) {
            (radioGroup_theme.getChildAt(i) as RadioButton).isEnabled = en
        }
    }

}
