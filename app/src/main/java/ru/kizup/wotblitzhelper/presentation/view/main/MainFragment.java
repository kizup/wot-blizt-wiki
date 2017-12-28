package ru.kizup.wotblitzhelper.presentation.view.main;

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
import ru.kizup.wotblitzhelper.base.BaseFragment;
import ru.kizup.wotblitzhelper.di.main.MainModule;
import ru.kizup.wotblitzhelper.presentation.view.achievements.AchievementsFragment;
import ru.kizup.wotblitzhelper.presentation.view.common_info.CommonInfoFragment;
import ru.kizup.wotblitzhelper.presentation.presenter.main.IMainPresenter;
import ru.kizup.wotblitzhelper.presentation.view.crew_skills.CrewSkillsFragment;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class MainFragment extends BaseFragment
        implements IMainView {

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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onPostViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);
        mMainPresenter.bindView(this);
    }

    @OnClick(R.id.bn_common_info)
    void onCommonInfoClick() {
        mMainPresenter.clickOnCommonInfo();
    }

    @OnClick(R.id.bn_achievements)
    void onAchievementsClick() {
        mMainPresenter.clickOnAchievements();
    }

    @OnClick(R.id.bn_crew_skills)
    void onCrewSkillsClick() {
        mMainPresenter.clickOnCrewSkills();
    }

    @Override
    public void showCommonInfoScreen() {
        showFragment(new CommonInfoFragment());
    }

    @Override
    public void showAchievementsScreen() {
        showFragment(new AchievementsFragment());
    }

    @Override
    public void showCrewSkillsScreen() {
        showFragment(new CrewSkillsFragment());
    }

    @Override
    public void onDestroyView() {
        mMainPresenter.unbindView();
        mUnbinder.unbind();
        super.onDestroyView();
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fl_container, fragment);
        transaction.commit();
    }
}
