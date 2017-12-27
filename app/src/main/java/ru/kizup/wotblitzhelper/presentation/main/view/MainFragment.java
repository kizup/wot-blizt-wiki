package ru.kizup.wotblitzhelper.presentation.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.HelperApp;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.di.main.MainModule;
import ru.kizup.wotblitzhelper.presentation.common_info.view.CommonInfoFragment;
import ru.kizup.wotblitzhelper.presentation.main.presenter.IMainPresenter;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class MainFragment extends Fragment implements IMainView {

    @Inject
    IMainPresenter mMainPresenter;

    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        HelperApp.get(getContext()).getAppComponent()
                .with(new MainModule())
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mUnbinder = ButterKnife.bind(this, root);
        mMainPresenter.bindView(this);
        return root;
    }

    @OnClick(R.id.bn_common_info)
    void onCommonInfoClick() {
        mMainPresenter.clickOnCommonInfo();
    }

    @Override
    public void showCommonInfoScreen() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fl_container, new CommonInfoFragment());
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        mMainPresenter.unbindView();
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
