package com.example.StepTracker;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.StepTracker.LoginActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class StartActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testNavigationToMainActivity() {
        onView(withId(R.id.usernameEditText)).perform(typeText("testUser"));
        onView(withId(R.id.passwordEditText)).perform(typeText("password123"));
        onView(withId(R.id.loginButton)).perform(click());

        // Prüfen, ob MainActivity geladen wird
        onView(withId(R.id.goalTextView)).check(matches(withText("0 von 10000 Schritten erreicht")));
    }
}
