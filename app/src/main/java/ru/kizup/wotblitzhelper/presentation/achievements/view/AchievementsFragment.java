package ru.kizup.wotblitzhelper.presentation.achievements.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.HelperApp;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.di.achievements.AchievementsModule;
import ru.kizup.wotblitzhelper.presentation.achievements.models.Achievement;
import ru.kizup.wotblitzhelper.presentation.achievements.presenter.IAchievementsPresenter;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsFragment extends Fragment implements IAchievementsView {

    @Inject
    IAchievementsPresenter mAchievementsPresenter;

    @BindView(R.id.loading)
    ProgressBar mProgressBar;
    @BindView(R.id.rv_achievements)
    RecyclerView mAchievementsRecyclerView;

    private Unbinder mUnbinder;
    private AchievementsAdapter mAchievementsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        HelperApp.get(getContext())
                .getAppComponent()
                .with(new AchievementsModule())
                .inject(this);
        mAchievementsAdapter = new AchievementsAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_achievements, container, false);
        mUnbinder = ButterKnife.bind(this, root);
        mAchievementsPresenter.bindView(this);
        mAchievementsPresenter.loadShortAchievementsInfo();

        mAchievementsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAchievementsRecyclerView.setAdapter(mAchievementsAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        mAchievementsPresenter.unbindView();
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showLoading() {
        mAchievementsRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoadedAchievements(List<Achievement> achievements) {
        mAchievementsRecyclerView.setVisibility(View.VISIBLE);
        mAchievementsAdapter.setItems(achievements);
    }
}
