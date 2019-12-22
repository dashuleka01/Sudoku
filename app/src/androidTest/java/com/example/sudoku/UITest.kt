package com.example.sudoku

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test


class UITest {

    @get:Rule
    var intentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Test
    fun testGame() {
        Espresso.onView(withId(R.id.imageButton_info)).perform(ViewActions.click())
        Intents.intended(hasComponent(InfoActivity::class.java.name))
        pressBack()
        Espresso.onView(withId(R.id.button_play)).perform(ViewActions.click())
        Intents.intended(hasComponent(LevelActivity::class.java.name))
        Espresso.onView(withId(R.id.button_level1)).perform(ViewActions.click())
        Intents.intended(hasComponent(GameActivity::class.java.name))
        Espresso.onView(withId(R.id.imageButton_settings)).perform(ViewActions.click())
        Intents.intended(hasComponent(SettingsActivity::class.java.name))
        Espresso.onView(withId(R.id.radioButton_night)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.radioButton_night)).check(matches(isChecked()))

        pressBack()
        Espresso.onView(withId(R.id.button_check)).check(matches(withText("Проверить")))
        pressBack()
        Espresso.onView(withId(R.id.button_level1)).check(matches(withText("1")))
        pressBack()
        Espresso.onView(withId(R.id.button_play)).check(matches(withText("Играть")))
        Espresso.onView(withId(R.id.imageButton_exit)).perform(ViewActions.click())

        try {
            Espresso.onView(withId(R.id.button_play)).check(matches(withText("Играть")))
            fail()
        }
        catch (e: RuntimeException){}

    }
}
