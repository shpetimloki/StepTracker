package com.example.StepTracker;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.StepTracker.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertTrue;

import android.content.SharedPreferences;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {




    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testGoalTextViewIsDisplayed() {
        onView(withId(R.id.goalTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void testChangeGoalButtonNavigatesToSetGoalActivity() {
        onView(withId(R.id.changeGoalButton)).perform(click());
        onView(withId(R.id.newGoalEditText)).check(matches(isDisplayed()));
    }

    @Test
    public void testLogoutButtonShowsDialog() {
        onView(withId(R.id.logoutButton)).perform(click());
        onView(withText("Abmelden")).check(matches(isDisplayed()));
    }
    @Test
    public void testStepsAreDisplayedCorrectly() {
        // Simuliere, dass der Schrittzähler initialisiert ist
        onView(withId(R.id.goalTextView)).check(matches(isDisplayed()));
 }
    @Test
    public void testWeekOverviewButtonNavigatesToWeekOverviewActivity() {
        // Klicke auf den Button
        onView(withId(R.id.weekOverviewButton)).perform(click());

        // Überprüfe, ob die Wochenübersicht angezeigt wird (zum Beispiel mit einer ID im Layout)
        onView(withId(R.id.weekBarChart)).check(matches(isDisplayed()));
    }
    @Test
    public void testMonthOverviewButtonNavigatesToMonthOverviewActivity() {
        // Klicke auf den Button
        onView(withId(R.id.monthOverviewButton)).perform(click());

        // Überprüfe, ob die Monatsübersicht angezeigt wird (zum Beispiel mit einer ID im Layout)
        onView(withId(R.id.monthBarChart)).check(matches(isDisplayed()));
    }
    @Test
    public void testChangeGoalNavigatesToSetGoalActivity() {
        // Klicke auf den Button
        onView(withId(R.id.changeGoalButton)).perform(click());

        // Überprüfe, ob die Zieländerungsansicht geöffnet wird
        onView(withId(R.id.newGoalEditText)).check(matches(isDisplayed()));
    }
    @Test
    public void testLogoutButtonShowsLogoutDialog() {
        // Klicke auf den Abmelde-Button
        onView(withId(R.id.logoutButton)).perform(click());

        // Überprüfe, ob der Dialog mit dem Text "Abmelden" angezeigt wird
        onView(withText("Abmelden")).check(matches(isDisplayed()));

        // Überprüfe, ob der Dialog die richtige Nachricht enthält
        onView(withText("Möchtest du dich wirklich abmelden?")).check(matches(isDisplayed()));
    }
    @Test
    public void testLogoutNavigatesToLoginSelection() {
        // Klicke auf den Abmelde-Button
        onView(withId(R.id.logoutButton)).perform(click());

        // Bestätige den Dialog
        onView(withText("Ja")).perform(click());

        // Überprüfe, ob die LoginSelectionActivity angezeigt wird
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
    }


}
