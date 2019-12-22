package com.example.sudoku

import org.junit.Test
import org.junit.Assert.*


class ExampleUnitTest {

    @Test
    fun testGame(){
        val game = Game(1)
        val list = game.getGame().toMutableList()
        var string = ""
        for (i in list)
            string += i.toString()
        assertEquals("017284359234159768589376142971542683325618497846793215792861534158437926463925871", string)
        assertFalse(game.check(list))
        list[0] = 6
        assertTrue(game.check(list))
    }

}
