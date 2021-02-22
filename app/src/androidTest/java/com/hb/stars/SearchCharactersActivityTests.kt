package com.hb.stars

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hb.stars.ui.search.SearchCharactersActivity
import com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint
import com.schibsted.spain.barista.assertion.BaristaImageViewAssertions.assertHasDrawable
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.interaction.BaristaListInteractions
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep


@RunWith(AndroidJUnit4ClassRunner::class)
class SearchCharactersActivityTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(SearchCharactersActivity::class.java)

    @Test
    fun verifyViews() {
        assertHasDrawable(R.id.img_background, R.drawable.starwars_wallpaper)
        assertDisplayed(R.id.tv_title, R.string.hey_there)
        assertNotDisplayed(R.id.img_no_character_found)
        assertHint(R.id.et_search, R.string.search_characters)
        clickOn(R.id.et_search)
    }

    @Test
    fun testViewsVisibility() {
        onView(withId(R.id.rv_characters)).check(matches(isDisplayed()))
        onView(withId(R.id.progress_circular)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testListListenerAndRecyclerView() {
        //onView(withId(R.id.et_search)).perform(clearText(), typeText("lu"))
        writeTo(R.id.et_search, "lu");
        sleep(4000)
        assertDisplayedAtPosition(R.id.rv_characters, 0, R.id.item_title, "Luke Skywalker");
        BaristaListInteractions.clickListItem(R.id.rv_characters, 0)
    }

}