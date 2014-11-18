package droidkit.inject;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;

import junit.framework.Assert;

import droidkit.inject.mock.InjectActivity;
import droidkit.inject.mock.InjectFragment;

/**
 * @author Daniel Serdyukov
 */
public class FragmentInjectionTest extends ActivityInstrumentationTestCase2<InjectActivity> {

    private InjectFragment mFragment;

    public FragmentInjectionTest() {
        super(InjectActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mFragment = new InjectFragment();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(droidkit.test.R.id.fragment, mFragment)
                .commit();
        getInstrumentation().waitForIdleSync();
    }

    public void testOnButton1Click() throws Exception {
        TouchUtils.clickView(this, mFragment.getButton1());
        getInstrumentation().waitForIdleSync();
        Assert.assertTrue(mFragment.isButton1Clicked());
        Assert.assertEquals(mFragment.getButton1(), mFragment.getClickedView1());
    }

    public void testOnButton2Click() throws Exception {
        TouchUtils.clickView(this, mFragment.getButton2());
        getInstrumentation().waitForIdleSync();
        Assert.assertTrue(mFragment.isButton2Clicked());
    }

    public void testOnButton3Click() throws Exception {
        TouchUtils.clickView(this, mFragment.getButton3());
        getInstrumentation().waitForIdleSync();
        Assert.assertTrue(mFragment.isButton3Clicked());
    }

    public void testOnCreateLoader() throws Exception {
        Assert.assertEquals(droidkit.test.R.id.mock_loader_2, mFragment.getOnCreateLoaderId());
    }

    public void testOnLoadFinished() throws Exception {
        Assert.assertEquals(InjectFragment.LOADER_2_RESULT, mFragment.getOnLoadFinishedResult());
    }


}
