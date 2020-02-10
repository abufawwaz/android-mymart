package com.example.mymart;


import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginCheck {

    @Rule
    public ActivityTestRule<LoginActivity> checkActivity
            = new ActivityTestRule<>(LoginActivity.class);


    @Test
    public void TestLoginUI() throws Exception{
        onView(withId(R.id.etusernamelogin)).perform(typeText("sandis"));
        onView(withId(R.id.etpasswordlogin)).perform(typeText("sandis"));

        onView(withId(R.id.displaylogin)).check(matches(withText("login success")));
    }

}