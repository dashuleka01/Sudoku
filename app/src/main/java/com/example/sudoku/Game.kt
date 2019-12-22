package com.example.sudoku

import android.util.Log

class Game {
    var list = MutableList(81){0}

//("617284359234159768589376142971542683325618497846793215792861534158437926463925871")
    constructor(level: Int){
        when(level){
            1 -> makearray("017284359234159768589376142971542683325618497846793215792861534158437926463925871")
            2 -> makearray("617284059204159768509376142971502683325618497840793015792061534158430926463905871")
            3 -> makearray("617284359234159768589376142971542683325618497846793215792861534158437926463925800")
            else -> Log.e("testme", "Error")
        }
    }

    fun getGame(): List<Int>{
        return list
    }

    fun setGame(l: List<Int>) {
        list  = l.toMutableList()
    }

    private fun makearray(string: String) {
        val level1 = string
        for(i in 0 until 81){
           list[i] = level1[i].toInt() - '0'.toInt()
        }
    }

    fun check(list: List<Int>): Boolean{
        val doublearray = Array(9) {Array(9){0}}
        var u = 0
        for (i in 0..8){
            for (j in 0..8){
                doublearray[i][j] = list[u++]
            }
        }

        var listLine = MutableList(9){0}
        var listColumn = MutableList(9){0}
        var listBox = MutableList(9){0}
        val correct = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val zeros = listOf<Int>(0, 0, 0, 0, 0, 0, 0, 0, 0)

        for (i in 0..8){
            for (j in 0..8){
                listLine[j] = doublearray[i][j]
                listColumn[j] = doublearray[j][i]
            }
            listLine.sort()
            listColumn.sort()
            if (listLine != correct || listColumn != correct) return false
            listLine = zeros.toMutableList()
            listColumn = zeros.toMutableList()

        }

        for (a in 0..2){
            for (b in 0..2){
                var t = 0
                for (j in 0..2){
                    for (k in 0..2) {
                        listBox[t++] = doublearray[a*3+j][b*3+k]
                    }
                }
                listBox.sort()
                if (listBox != correct) return false
                listBox = zeros.toMutableList()
            }
        }
        return true
    }
}