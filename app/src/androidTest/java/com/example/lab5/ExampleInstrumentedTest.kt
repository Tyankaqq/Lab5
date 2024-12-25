package com.example.lab5

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testPriceCalculation() {
        Espresso.onView(withId(R.id.quantityEditText)).perform(ViewActions.typeText("10"))

        Espresso.onView(withId(R.id.sizeSpinner)).perform(ViewActions.click())
        Espresso.onView(withText("10x15")).perform(ViewActions.click())

        Espresso.onView(withId(R.id.paperTypeSpinner)).perform(ViewActions.click())
        Espresso.onView(withText("Глянцевая")).perform(ViewActions.click())

        Espresso.onView(withId(R.id.calculateButton)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.resultTextView))
            .check(ViewAssertions.matches(withText("Стоимость: 720.00 рублей")))
    }

    @Test
    fun testInvalidPriceCalculation() {
        Espresso.onView(withId(R.id.quantityEditText)).perform(ViewActions.typeText("abc"))

        Espresso.onView(withId(R.id.sizeSpinner)).perform(ViewActions.click())
        Espresso.onView(withText("10x15")).perform(ViewActions.click())

        Espresso.onView(withId(R.id.paperTypeSpinner)).perform(ViewActions.click())
        Espresso.onView(withText("Глянцевая")).perform(ViewActions.click())

        Espresso.onView(withId(R.id.calculateButton)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.resultTextView))
            .check(ViewAssertions.matches(withText("Стоимость: 0.00 рублей")))
    }
}
