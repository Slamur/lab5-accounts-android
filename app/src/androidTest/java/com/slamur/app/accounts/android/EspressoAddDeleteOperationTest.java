package com.slamur.app.accounts.android;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.base.Preconditions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.slamur.app.accounts.android.activity.operation.OperationListActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class EspressoAddDeleteOperationTest {

    @Rule
    public final ActivityTestRule<OperationListActivity> operation = new ActivityTestRule<OperationListActivity>(OperationListActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            return new Intent(targetContext, OperationListActivity.class);
        }
    };

    public static Matcher<View> hasSibling2(final Matcher<View> siblingMatcher) {
        Preconditions.checkNotNull(siblingMatcher);
        return new TypeSafeMatcher() {
            @Override
            protected boolean matchesSafely(Object o) {
                View view = (View) o;
                ViewParent parent = view.getParent();
                parent = parent.getParent();
                if(!(parent instanceof ViewGroup)) {
                    return false;
                } else {
                    ViewGroup parentGroup = (ViewGroup)parent;

                    for(int i = 0; i < parentGroup.getChildCount(); ++i) {
                        View child = parentGroup.getChildAt(i);
                        if (child instanceof TextView) {
                            i = i;
                        }
                        if(siblingMatcher.matches(child)) {
                            return true;
                        }
                    }

                    return false;
                }
            }

            public void describeTo(Description description) {
                description.appendText("has sibling: ");
                siblingMatcher.describeTo(description);
            }
        };
    }

    @Test
    public void addDeleteOperationEspresso() {
        onView(withId(R.id.activity_list_operations_add_button)).perform(click());
        onView(withId(R.id.activity_info_operation_add_button)).check(matches(allOf(withId(R.id.activity_info_operation_add_button), isDisplayed())));
        onView(withId(R.id.activity_info_operation_value_edit)).perform(typeText("100.05"));
        onView(withId(R.id.activity_info_operation_description_edit)).perform(typeText("[EspressoTestDesc]"));
        onView(withId(R.id.activity_info_operation_add_button)).perform(click());

//        String incomeCategoryName = CategoryBaseDao.getInstance(InstrumentationRegistry.getTargetContext()).filterCategoriesOfType(OperationType.INCOME).get(0).getName();
        closeSoftKeyboard();
        onView(allOf(withId(R.id.list_item_operation_remove), hasSibling2(withText("Target: hhhhh")))).perform(click());
    }
}
