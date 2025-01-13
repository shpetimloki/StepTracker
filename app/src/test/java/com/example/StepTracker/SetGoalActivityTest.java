package com.example.StepTracker;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.example.StepTracker.SetGoalActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SetGoalActivityTest {

    @Rule
    public ActivityScenarioRule<SetGoalActivity> activityRule =
            new ActivityScenarioRule<>(SetGoalActivity.class);

    @Test
    public void testSetDailyGoal() {
        onView(withId(R.id.newGoalEditText)).perform(typeText("15000"));
        onView(withId(R.id.saveGoalButton)).perform(click());

        // Hier kannst du prüfen, ob das Ziel gespeichert wurde
        // Zum Beispiel durch einen Toast- oder ViewCheck
    }
}
