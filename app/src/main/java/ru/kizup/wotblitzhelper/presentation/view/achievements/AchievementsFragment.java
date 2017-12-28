package ru.kizup.wotblitzhelper.presentation.view.achievements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.HelperApp;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.base.BaseFragment;
import ru.kizup.wotblitzhelper.di.achievements.AchievementsModule;
import ru.kizup.wotblitzhelper.models.achievements.AchievementUIModel;
import ru.kizup.wotblitzhelper.presentation.presenter.achievements.IAchievementsPresenter;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsFragment extends BaseFragment
        implements IAchievementsView,
        OnItemClickListener<AchievementUIModel> {

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
        mAchievementsAdapter = new AchievementsAdapter(getContext(), this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_achievements;
    }

    @Override
    public void onPostViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);
        mAchievementsPresenter.bindView(this);
        mAchievementsPresenter.loadShortAchievementsInfo();

        mAchievementsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAchievementsRecyclerView.setAdapter(mAchievementsAdapter);
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
    public void showLoadedAchievements(List<AchievementUIModel> achievementUIModels) {
        mAchievementsRecyclerView.setVisibility(View.VISIBLE);
        mAchievementsAdapter.setItems(achievementUIModels);
    }

    @Override
    public void showDetailAchievementDialog(AchievementUIModel model) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_detail_achievement,
                (ViewGroup) getView(),
                false);

        ImageView bigImage = dialogView.findViewById(R.id.iv_achievement_big);
        TextView name = dialogView.findViewById(R.id.tv_achievement_name);
        TextView description = dialogView.findViewById(R.id.tv_achievement_description);

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .create();

        dialog.show();
        name.setText(model.getName());
        description.setText(model.getDescription());
        Picasso.with(getContext())
                .load(model.getImageBig())
                .into(bigImage);
    }

    @Override
    public void onItemClick(AchievementUIModel item) {
        mAchievementsPresenter.onAchievementClick(item);
    }
}
