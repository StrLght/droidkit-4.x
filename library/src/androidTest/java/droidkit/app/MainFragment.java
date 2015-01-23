package droidkit.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import droidkit.annotation.InjectView;
import droidkit.annotation.OnActionClick;
import droidkit.annotation.OnClick;

/**
 * @author Daniel Serdyukov
 */
public class MainFragment extends Fragment {

    @InjectView(droidkit.test.R.id.fmt_text1)
    TextView mText1;

    boolean mButton1Clicked;

    View mButton2;

    MenuItem mAddMenuItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(droidkit.test.R.layout.fmt_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(droidkit.test.R.menu.fmt_main, menu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(droidkit.test.R.id.fmt_button1)
    void onButton1Click() {
        mButton1Clicked = true;
    }

    @OnClick(droidkit.test.R.id.fmt_button2)
    void onButton2Click(View v) {
        mButton2 = v;
    }

    @OnActionClick(droidkit.test.R.id.action_add)
    void onAddAction(MenuItem item) {
        mAddMenuItem = item;
    }

}
