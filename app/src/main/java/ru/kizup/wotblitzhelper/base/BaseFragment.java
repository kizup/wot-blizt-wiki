package ru.kizup.wotblitzhelper.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by: dpuzikov on 19.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public abstract class BaseFragment extends Fragment
        implements BaseView {

    public abstract @LayoutRes int getLayoutId();

    public abstract void onPostViewCreated(View view, @Nullable Bundle savedInstanceState);

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

}
