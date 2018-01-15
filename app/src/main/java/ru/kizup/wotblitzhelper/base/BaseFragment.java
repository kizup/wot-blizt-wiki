package ru.kizup.wotblitzhelper.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ru.kizup.wotblitzhelper.R;

/**
 * Created by: dpuzikov on 19.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public abstract class BaseFragment extends Fragment
        implements BaseView {

    public abstract @LayoutRes int getLayoutId();

    public abstract void onPostViewCreated(View view, @Nullable Bundle savedInstanceState);

    private OnBaseFragmentInteractionListener mBaseFragmentInteractionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseFragmentInteractionListener = (OnBaseFragmentInteractionListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        onPostViewCreated(view, savedInstanceState);
    }

    @Override
    public void showMessage(String message) {
        showSnackbar(message);
    }

    public void showMessage(int resId) {
        showSnackbar(resId);
    }

    protected void showSnackbar(int resId) {
        showSnackbar(getString(resId));
    }

    protected void showSnackbar(String message) {
        if (getView() != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected void setToolbar(Toolbar toolbar) {
        getBaseActivity().setSupportActionBar(toolbar);
    }

    protected void setShowBackArrow() {
        ActionBar actionBar = getBaseActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        transaction.replace(R.id.fl_container, fragment);
        transaction.commit();
    }

    public interface OnBaseFragmentInteractionListener {

        void onBackArrowClicked();

    }

}
