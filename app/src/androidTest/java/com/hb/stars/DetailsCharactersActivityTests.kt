package com.hb.stars

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hb.stars.ui.details.DetailsCharactersActivity
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailsCharactersActivityTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(DetailsCharactersActivity::class.java)

    @Test
    fun verifyViews() {
        assertDisplayed(R.id.tv_details_name, R.string.name)
        assertDisplayed(R.id.tv_details_height, R.string.height)
        assertDisplayed(R.id.tv_details_birth_year, R.string.birth_year)
    }

    @Test
    fun testViewsVisibilityOnAppLaunch() {
        onView(withId(R.id.card_movies)).check(matches(not(isDisplayed())))
        onView(withId(R.id.card_species)).check(matches(not(isDisplayed())))
    }
}
