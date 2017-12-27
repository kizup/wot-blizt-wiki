package ru.kizup.wotblitzhelper.presentation.common_info.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.HelperApp;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.di.common_info.CommonInfoModule;
import ru.kizup.wotblitzhelper.presentation.achievements.view.AchievementsFragment;
import ru.kizup.wotblitzhelper.presentation.common_info.presenter.ICommonInfoPresenter;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoFragment extends Fragment
        implements ICommonInfoView {

    @Inject
    ICommonInfoPresenter mCommonInfoPresenter;

    @BindView(R.id.tv_game_updated_at)
    TextView mGameUpdatedAtTextView;
    @BindView(R.id.tv_game_version)
    TextView mGameVersionTextView;
    @BindView(R.id.tv_vehicle_types_count)
    TextView mVehicleTypesCount;
    @BindView(R.id.tv_error)
    TextView mErrorTextView;
    @BindView(R.id.loading)
    ProgressBar mLoadingIndicator;

    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        HelperApp.get(getContext()).getAppComponent()
                .with(new CommonInfoModule())
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_common_info, container, false);
        mUnbinder = ButterKnife.bind(this, root);
        mCommonInfoPresenter.bindView(this);
        mCommonInfoPresenter.loadCommonInfo();
        return root;
    }

    @Override
    public void onDestroyView() {
        mCommonInfoPresenter.unbindView();
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showLoading() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mErrorTextView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mLoadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showFailureResponseError(String description) {
        mErrorTextView.setVisibility(View.VISIBLE);
        mErrorTextView.setText(description);
    }

    @Override
    public void showCommonError() {
        mErrorTextView.setVisibility(View.VISIBLE);
        mErrorTextView.setText(R.string.unknown_error);
    }

    @Override
    public void setUpdatedAt(String formattedDate) {
        mGameUpdatedAtTextView.setText(formattedDate);
    }

    @Override
    public void setGameVersion(String version) {
        mGameVersionTextView.setText(version);
    }

    @Override
    public void setVehicleTypesCount(int size) {
        mVehicleTypesCount.setText(String.valueOf(size));
    }

    @Override
    public void showAchievementsScreen() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fl_container, new AchievementsFragment());
        transaction.commit();
    }

    @OnClick(R.id.bn_achievements)
    void onAchievementsClick() {
        mCommonInfoPresenter.clickAchievementsButton();
    }

}
