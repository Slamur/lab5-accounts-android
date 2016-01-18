package com.slamur.app.accounts.android;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.slamur.app.accounts.android.activity.operation.OperationListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class EspressoSearchOperationsTest {

    @Rule
    public final ActivityTestRule<OperationListActivity> searchOperation = new ActivityTestRule<OperationListActivity>(OperationListActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            return new Intent(targetContext, OperationListActivity.class);
        }
    };

    @Test
    public void addDeleteAccountEspresso() {
        onView(withId(R.id.search_activity_widget_edit)).perform(typeText("test"));
        onView(withId(R.id.search_activity_widget_button)).perform(click());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
