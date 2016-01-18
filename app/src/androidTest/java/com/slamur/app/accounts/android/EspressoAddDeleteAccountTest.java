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

import com.slamur.app.accounts.android.activity.account.AccountListActivity;
import com.slamur.app.accounts.android.list_view.account.AccountListAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class EspressoAddDeleteAccountTest {

    @Rule
    public final ActivityTestRule<AccountListActivity> account = new ActivityTestRule<AccountListActivity>(AccountListActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            return new Intent(targetContext, AccountListActivity.class);//.putExtra(InfoItemActivity.ACTION_TYPE_EXTRA, InfoItemActivity.ADD_ITEM_ACTION);
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
                        if(siblingMatcher.matches(parentGroup.getChildAt(i))) {
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
    public void addDeleteAccountEspresso() {
//        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withId(R.id.account_list_add_button)).perform(click());
        onView(withId(R.id.account_info_add_button)).check(matches(allOf(withId(R.id.account_info_add_button), isDisplayed())));
        onView(withId(R.id.account_name_edit)).perform(typeText("[EspressoTest]"));
        onView(withId(R.id.account_balance_edit)).perform(typeText("100.05"));
        onView(withId(R.id.account_description_edit)).perform(typeText("[EspressoTestDesc]"));
        onView(withId(R.id.account_info_add_button)).perform(click());
        onView(allOf(withId(R.id.account_list_item_remove), hasSibling2(withText("Name: [EspressoTest]")))).perform(click());
        onView(withText(AccountListAdapter.REMOVE_ACCOUNT_DIALOG_TITLE))
                .check(matches(allOf(withText(AccountListAdapter.REMOVE_ACCOUNT_DIALOG_TITLE), isDisplayed())));
        onView(withId(R.id.dialog_list_item_account_delete_button)).perform(click());
    }
}
