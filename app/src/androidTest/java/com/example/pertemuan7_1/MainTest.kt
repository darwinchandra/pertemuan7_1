package com.example.pertemuan7_1

import android.content.Intent
import android.net.Uri
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
class MainTest {@Rule @JvmField
var activityTestRule = ActivityTestRule(MainActivity::class.java)
    @Test
    fun clickUppercaseButton() {
        onView(withId(R.id.nama)).perform(
            ViewActions
                .typeText("helloworld"))
        onView(withId(R.id.myUppercase)).perform(ViewActions.click())
        onView(withId(R.id.nama))
            .check(matches(withText("HELLOWORLD")))
    }
    @Before
    fun setUp() {
        Intents.init()
    }
    @Test
    fun launchBrowser() {
        val expectedIntent : org.hamcrest.Matcher<Intent>? =
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("https://www.mikroskil.ac.id/")))//intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))
        onView(withId(R.id.startSecondActivity)).perform(click())
        intended(expectedIntent)
    }
    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun test_gotosecond(){
        onView(withId(R.id.gotosecond)).perform(click())
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
    }

    @Test
    fun testback(){
        onView(withId(R.id.gotosecond)).perform(click())
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.main)).check(matches(isDisplayed()))

    }

}

