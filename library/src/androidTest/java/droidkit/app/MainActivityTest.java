package droidkit.app;

import android.app.Fragment;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import junit.framework.Assert;

import droidkit.content.FakeLoader;
import droidkit.database.CursorUtils;
import droidkit.view.Views;

/**
 * @author Daniel Serdyukov
 */
@LargeTest
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    public void testInjectView() throws Exception {
        final View expected = Views.findById(mActivity, droidkit.test.R.id.text1);
        Assert.assertNotNull(expected);
        assertEquals(expected, mActivity.mText1);
    }

    public void testOnClick() throws Exception {
        Espresso.onView(ViewMatchers
                .withId(droidkit.test.R.id.button1))
                .perform(ViewActions.click());
        Assert.assertTrue(mActivity.mButton1Clicked);
        Espresso.onView(ViewMatchers
                .withId(droidkit.test.R.id.button2))
                .perform(ViewActions.click());
        Assert.assertNotNull(mActivity.mButton2);
    }

    public void testOnActionClick() throws Exception {
        Espresso.onView(ViewMatchers
                .withId(droidkit.test.R.id.action_settings))
                .perform(ViewActions.click());
        Assert.assertTrue(mActivity.mSettingsActionClicked);
    }

    public void testFragmentInjectView() throws Exception {
        final View expected = Views.findById(mActivity, droidkit.test.R.id.fmt_text1);
        Assert.assertNotNull(expected);
        final Fragment fragment = mActivity.getFragmentManager()
                .findFragmentById(droidkit.test.R.id.content);
        Assert.assertNotNull(fragment);
        assertEquals(expected, ((MainFragment) fragment).mText1);
    }

    public void testFragmentOnClick() throws Exception {
        final Fragment fragment = mActivity.getFragmentManager()
                .findFragmentById(droidkit.test.R.id.content);
        Assert.assertNotNull(fragment);
        Espresso.onView(ViewMatchers
                .withId(droidkit.test.R.id.fmt_button1))
                .perform(ViewActions.click());
        Assert.assertTrue(((MainFragment) fragment).mButton1Clicked);
        Espresso.onView(ViewMatchers
                .withId(droidkit.test.R.id.fmt_button2))
                .perform(ViewActions.click());
        Assert.assertNotNull(((MainFragment) fragment).mButton2);
    }

    public void testFragmentOnActionClick() throws Exception {
        final Fragment fragment = mActivity.getFragmentManager()
                .findFragmentById(droidkit.test.R.id.content);
        Assert.assertNotNull(fragment);
        Espresso.onView(ViewMatchers
                .withId(droidkit.test.R.id.action_add))
                .perform(ViewActions.click());
        Assert.assertNotNull(((MainFragment) fragment).mAddMenuItem);
    }

    public void testLoaderCallbacks() throws Exception {
        Assert.assertNotNull(mActivity.mFakeCursor);
        Assert.assertTrue(mActivity.mFakeCursor.moveToFirst());
        Assert.assertEquals(MainActivity.class.getSimpleName(),
                CursorUtils.getString(mActivity.mFakeCursor, FakeLoader.NAME));
    }

}
