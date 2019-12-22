package com.example.sudoku

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

class Change(private val activity1: Activity) : Activity() {

    fun restartAct(activity: Activity){
        val context = activity.applicationContext
        val i = Intent(context, activity.javaClass).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(i)
        activity1.finish()
    }

    fun restartAct(activity: Activity, level: Int){
        val context = activity.applicationContext
        val i = Intent(context, activity.javaClass).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.putExtra("level", level)
        context.startActivity(i)
        activity1.finish()
    }

    fun changeTheme(activity: Activity, view: View): Boolean{
        val m = AppCompatDelegate.getDefaultNightMode()
        val tag = view.tag.toString()
        if (m == AppCompatDelegate.MODE_NIGHT_NO && tag == "night"  ||
            m == AppCompatDelegate.MODE_NIGHT_YES && tag == "day"){
            restartAct(activity)
            return true
        }
        return false
    }

    fun changeTheme(activity: Activity, view: View, level: Int):Boolean{
        val m = AppCompatDelegate.getDefaultNightMode()
        val tag = view.tag.toString()
        if (m == AppCompatDelegate.MODE_NIGHT_NO && tag == "night"  ||
            m == AppCompatDelegate.MODE_NIGHT_YES && tag == "day"){
            restartAct(activity, level)
            return true
        }
        return false
    }
}
