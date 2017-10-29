package com.telecorp.dashqueue;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.telecorp.dashqueue.api.model.HospitalItem;
import com.telecorp.dashqueue.ui.main.MainActivity;
import com.telecorp.dashqueue.ui.main.contract.MainActivityPresenter;
import com.telecorp.dashqueue.utils.EspressoIdlingResource;
import com.telecorp.dashqueue.utils.schedulers.ImmediateSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityScreenTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.telecorp.dashqueue", appContext.getPackageName());
    }

    private static ArrayList<HospitalItem> DATAS;


    private MainActivityPresenter mPresenter;
    private ImmediateSchedulerProvider mSchedulerProvider;

    @Rule
    public ActivityTestRule<MainActivity> mTasksActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    /**
     * Prepare your test fixture for this test. In this case we register an IdlingResources with
     * Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
     * idle state. This helps Espresso to synchronize your test actions, which makes tests
     * significantly more reliable.
     */
    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void clickAddTaskButton_opensAddTaskUi() {
        // Click on the add task button

        onView(withId(R.id.swiperefreshlayout_main)).perform(ViewActions.swipeDown());

        // Check if the add task screen is displayed
        onView(withId(R.id.swiperefreshlayout_main)).check(matches(isDisplayed()));
    }
}
