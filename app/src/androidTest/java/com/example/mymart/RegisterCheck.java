package com.example.mymart;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Toast;

import androidx.test.rule.ActivityTestRule;

import com.example.mymart.businessLogic.RegisterUser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterCheck {

    @Rule
   public ActivityTestRule<RegisterActivity> checkActivity
           = new ActivityTestRule<>(RegisterActivity.class);


   @Test
   public void TestUI() throws Exception{
       onView(withId(R.id.usernameregister)).perform(typeText("sandis"));
       onView(withId(R.id.passwordregister)).perform(typeText("sandis"));
       onView(withId(R.id.addressregister)).perform(typeText("bansbari"));
       onView(withId(R.id.contactregister)).perform(typeText("89862"));
       onView(withId(R.id.genderregister)).perform(typeText("male"));

       onView(withId(R.id.display)).check(matches(withText("Registered")));
   }

}
