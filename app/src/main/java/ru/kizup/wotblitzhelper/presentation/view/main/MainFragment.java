package ru.kizup.wotblitzhelper.presentation.view.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.HelperApp;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.base.BaseFragment;
import ru.kizup.wotblitzhelper.di.main.MainModule;
import ru.kizup.wotblitzhelper.presentation.presenter.main.IMainPresenter;
import ru.kizup.wotblitzhelper.presentation.view.achievements.AchievementsFragment;
import ru.kizup.wotblitzhelper.presentation.view.common_info.CommonInfoFragment;
import ru.kizup.wotblitzhelper.presentation.view.crew_skills.CrewSkillsFragment;
import ru.kizup.wotblitzhelper.presentation.view.vehicles_grid.VehiclesGridFragment;

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
    private ProgressDialog mProgressDialog;

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

    @OnClick(R.id.bn_vehicles)
    void onVehiclesClick() {
        mMainPresenter.clickOnVehicles();
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
    public void showVehiclesScreen() {
        showFragment(VehiclesGridFragment.newInstance());
    }

    @Override
    public void showRequestUpdateDatabaseDialog() {
        new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom)
                .setTitle("Database is out of date")
                .setMessage("Do you want update database?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> mMainPresenter.updateDatabase())
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    @Override
    public void showUpdateDialog() {
        mProgressDialog = new ProgressDialog(getContext(), R.style.AlertDialogCustom);
        mProgressDialog.setMessage("Updating database...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void hideUpdateDialog() {
        if (mProgressDialog != null) mProgressDialog.dismiss();
    }

    @Override
    public void onDestroyView() {
        mMainPresenter.unbindView();
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
