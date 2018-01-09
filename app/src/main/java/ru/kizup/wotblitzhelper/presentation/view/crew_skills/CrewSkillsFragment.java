package ru.kizup.wotblitzhelper.presentation.view.crew_skills;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.HelperApp;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.base.BaseFragment;
import ru.kizup.wotblitzhelper.di.crew_skills.CrewSkillsModule;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillSectionUIModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;
import ru.kizup.wotblitzhelper.presentation.presenter.crew_skills.ICrewSkillsPresenter;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillsFragment extends BaseFragment
        implements ICrewSkillsView,
        OnItemClickListener<CrewSkillUIModel> {

    @Inject
    ICrewSkillsPresenter mCrewSkillsPresenter;

    @BindView(R.id.rv_crew_skills)
    RecyclerView mCrewSkillsRecyclerView;
    @BindView(R.id.loading)
    ProgressBar mProgressBar;
    @BindView(R.id.tv_error_load_crew_skills)
    TextView mErrorLoadCrewSkillsTextView;
    @BindView(R.id.crew_skill_side_panel)
    ScrollView mSidePanel;
    @BindView(R.id.tv_crew_skill_name)
    TextView mSkillNameTextView;
    @BindView(R.id.tv_crew_skill_tip)
    TextView mTipTextView;
    @BindView(R.id.tv_crew_skill_tip_label)
    TextView mTipTitleTextView;
    @BindView(R.id.tv_crew_skill_features)
    TextView mFeaturesTextView;
    @BindView(R.id.tv_crew_skill_features_label)
    TextView mFeaturesLabelTextView;
    @BindView(R.id.tv_crew_skill_effect)
    TextView mEffectTextView;
    @BindView(R.id.tv_crew_skill_effect_label)
    TextView mEffectLabelTextView;
    @BindView(R.id.iv_crew_skill)
    ImageView mSkillImageView;
    @BindView(R.id.tv_empty_crew_skill_side_panel)
    TextView mHintEmptySidePanel;
    @BindView(R.id.crew_skill_side_panel_content)
    ViewGroup mSidePanelContent;

    private Unbinder mUnbinder;
    private CrewSkillsAdapter mCrewSkillsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        HelperApp.get(getContext())
                .getAppComponent()
                .with(new CrewSkillsModule())
                .inject(this);
        mCrewSkillsAdapter = new CrewSkillsAdapter(getContext(), this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_crew_skills;
    }

    @Override
    public void onPostViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);
        mCrewSkillsPresenter.bindView(this);
        mCrewSkillsPresenter.setScreenOrientation(getResources().getConfiguration().orientation);
        mCrewSkillsPresenter.loadCrewSkills();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);

        mCrewSkillsAdapter.setLayoutManager(manager);
        mCrewSkillsRecyclerView.setLayoutManager(manager);
        mCrewSkillsRecyclerView.setAdapter(mCrewSkillsAdapter);
    }

    @Override
    public void onDestroyView() {
        mCrewSkillsPresenter.unbindView();
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        mCrewSkillsRecyclerView.setVisibility(View.GONE);
        mErrorLoadCrewSkillsTextView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLoadCrewSkills(String error) {
        mSidePanel.setVisibility(View.GONE);
        mCrewSkillsRecyclerView.setVisibility(View.GONE);
        mErrorLoadCrewSkillsTextView.setVisibility(View.VISIBLE);
        mErrorLoadCrewSkillsTextView.setText(error);
    }

    @Override
    public void showLoadedCrewSkills(Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> models) {
        mCrewSkillsRecyclerView.setVisibility(View.VISIBLE);
        mCrewSkillsAdapter.setItems(models);
    }

    @Override
    public void showDetailCrewSkillInfo(CrewSkillUIModel model) {
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_detail_crew_skill, (ViewGroup) getView(), false);
        TextView name = dialogView.findViewById(R.id.tv_crew_skill_name);
        TextView tip = dialogView.findViewById(R.id.tv_crew_skill_tip);
        TextView tipLabel = dialogView.findViewById(R.id.tv_crew_skill_tip_label);
        TextView features = dialogView.findViewById(R.id.tv_crew_skill_features);
        TextView featuresLabel = dialogView.findViewById(R.id.tv_crew_skill_features_label);
        TextView effect = dialogView.findViewById(R.id.tv_crew_skill_effect);
        TextView effectLabel = dialogView.findViewById(R.id.tv_crew_skill_effect_label);
        ImageView skillImage = dialogView.findViewById(R.id.iv_crew_skill);

        name.setText(model.getName());

        dialog.setContentView(dialogView);
        dialog.show();

        if (TextUtils.isEmpty(model.getFeatures())) {
            features.setVisibility(View.GONE);
            featuresLabel.setVisibility(View.GONE);
        } else {
            features.setVisibility(View.VISIBLE);
            featuresLabel.setVisibility(View.VISIBLE);
            features.setText(model.getFeatures());
        }

        if (TextUtils.isEmpty(model.getTip())) {
            tip.setVisibility(View.GONE);
            tipLabel.setVisibility(View.GONE);
        } else {
            tip.setVisibility(View.VISIBLE);
            tipLabel.setVisibility(View.VISIBLE);
            tip.setText(model.getTip());
        }

        if (TextUtils.isEmpty(model.getEffect())) {
            effect.setVisibility(View.GONE);
            effectLabel.setVisibility(View.GONE);
        } else {
            effect.setVisibility(View.VISIBLE);
            effectLabel.setVisibility(View.VISIBLE);
            effect.setText(model.getEffect());
        }

        Picasso.with(getContext())
                .load(model.getImageUrl())
                .into(skillImage);
    }

    @Override
    public void showEmptySidePanel() {
        mSidePanelContent.setVisibility(View.GONE);
        mHintEmptySidePanel.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSidePanel() {
        mSidePanel.setVisibility(View.GONE);
    }

    @Override
    public void showSidePanel(CrewSkillUIModel model) {
        mHintEmptySidePanel.setVisibility(View.GONE);
        mSidePanelContent.setVisibility(View.VISIBLE);
        mSkillNameTextView.setText(model.getName());

        if (TextUtils.isEmpty(model.getFeatures())) {
            mFeaturesTextView.setVisibility(View.GONE);
            mFeaturesLabelTextView.setVisibility(View.GONE);
        } else {
            mFeaturesTextView.setVisibility(View.VISIBLE);
            mFeaturesLabelTextView.setVisibility(View.VISIBLE);
            mFeaturesTextView.setText(model.getFeatures());
        }

        if (TextUtils.isEmpty(model.getTip())) {
            mTipTextView.setVisibility(View.GONE);
            mTipTitleTextView.setVisibility(View.GONE);
        } else {
            mTipTextView.setVisibility(View.VISIBLE);
            mTipTitleTextView.setVisibility(View.VISIBLE);
            mTipTextView.setText(model.getTip());
        }

        if (TextUtils.isEmpty(model.getEffect())) {
            mEffectTextView.setVisibility(View.GONE);
            mEffectLabelTextView.setVisibility(View.GONE);
        } else {
            mEffectTextView.setVisibility(View.VISIBLE);
            mEffectLabelTextView.setVisibility(View.VISIBLE);
            mEffectTextView.setText(model.getEffect());
        }

        Picasso.with(getContext())
                .load(model.getImageUrl())
                .into(mSkillImageView);
    }

    @Override
    public void onItemClick(CrewSkillUIModel item) {
        mCrewSkillsPresenter.clickOnCrewSkill(item);
    }
}
