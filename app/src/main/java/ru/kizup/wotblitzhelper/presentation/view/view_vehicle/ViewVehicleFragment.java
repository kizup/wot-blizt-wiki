package ru.kizup.wotblitzhelper.presentation.view.view_vehicle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.transition.Fade;
import android.support.transition.Slide;
import android.support.transition.TransitionManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.HelperApp;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.base.BaseFragment;
import ru.kizup.wotblitzhelper.di.view_vehicle.ViewVehicleModule;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ArmorDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.VehicleModule;
import ru.kizup.wotblitzhelper.presentation.presenter.view_vehicle.IViewVehiclePresenter;
import ru.kizup.wotblitzhelper.presentation.view.custom.ModuleButton;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ViewVehicleFragment extends BaseFragment
        implements IViewVehicleView,
        OnItemClickListener<ShortVehicleInfoUIModel> {

    private static final String VEHICLE_ID_ARG = "vehicle_id_arg";

    @Inject
    IViewVehiclePresenter mPresenter;

    @Inject
    Picasso mPicasso;

    @BindView(R.id.loading)
    ProgressBar mLoadingIndicator;
    @BindView(R.id.sv_vehicle_info_container)
    ViewGroup mVehicleInfoContainer;

    @Nullable
    @BindView(R.id.tv_vehicle_name)
    TextView mVehicleNameTextView;
    @BindView(R.id.iv_vehicle)
    ImageView mVehicleImageView;
    @BindView(R.id.tv_vehicle_description)
    TextView mVehicleDescription;
    @BindView(R.id.tv_vehicle_type)
    TextView mVehicleTypeTextView;
    @BindView(R.id.tv_vehicle_tier)
    TextView mVehicleTierTextView;
    @BindView(R.id.tv_vehicle_nation)
    TextView mVehicleNationTextView;
    @BindView(R.id.iv_vehicle_type)
    ImageView mVehicleTypeImageView;

    @BindView(R.id.next_tanks_container)
    ViewGroup mNextTanksContainer;
    @BindView(R.id.rv_next_tanks)
    RecyclerView mNextTanksRecyclerView;

    @BindView(R.id.vehicle_profile)
    ViewGroup mVehicleProfileContainer;
    @BindView(R.id.tv_firepower)
    TextView mFirePowerTextView;
    @BindView(R.id.tv_maneuverability)
    TextView mManeuverabilityTextView;
    @BindView(R.id.tv_protection)
    TextView mProtectionTextView;
    @BindView(R.id.tv_shot_efficiency)
    TextView mShotEfficiencyTextView;

    @BindView(R.id.tv_armor_hull_front)
    TextView mArmorHullFrontTextView;
    @BindView(R.id.tv_armor_turret_front)
    TextView mArmorTurretFrontTextView;
    @BindView(R.id.tv_armor_hull_sides)
    TextView mArmorHullSidesTextView;
    @BindView(R.id.tv_armor_turret_sides)
    TextView mArmorTurretSidesTextView;
    @BindView(R.id.tv_armor_hull_rear)
    TextView mArmorHullRearTextView;
    @BindView(R.id.tv_armor_turret_rear)
    TextView mArmorTurretRearTextView;

    @BindView(R.id.iv_premium)
    ImageView mPremiumImageView;

    @BindView(R.id.modules_container)
    ViewGroup mModulesContainer;
    @BindView(R.id.modules_container_shadow)
    View mModulesContainerShadow;
    @BindView(R.id.mb_engine)
    ModuleButton mEngineModuleButton;
    @BindView(R.id.mb_gun)
    ModuleButton mGunModuleButton;
    @BindView(R.id.mb_suspension)
    ModuleButton mSuspensionModuleButton;
    @BindView(R.id.mb_turret)
    ModuleButton mTurretModuleButton;
    @BindView(R.id.rv_vehicle_modules)
    RecyclerView mVehicleModulesRecyclerView;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Unbinder mUnbinder;
    private NextTanksAdapter mNextTanksAdapter;
    private VehicleModulesAdapter mVehicleModulesAdapter;

    public static ViewVehicleFragment newInstance(int vehicleId) {
        Bundle args = new Bundle();
        args.putInt(VEHICLE_ID_ARG, vehicleId);
        ViewVehicleFragment fragment = new ViewVehicleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        HelperApp.get(getContext())
                .getAppComponent()
                .with(new ViewVehicleModule())
                .inject(this);

        mNextTanksAdapter = new NextTanksAdapter(getContext(), mPicasso, this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_view_vehicle;
    }

    @Override
    public void onPostViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);

        mNextTanksRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mNextTanksRecyclerView.setAdapter(mNextTanksAdapter);
        mNextTanksRecyclerView.setNestedScrollingEnabled(false);

        setToolbar(mToolbar);
        setShowBackArrow();

        mCollapsingToolbarLayout.setTitle("");
        mToolbar.setTitle("");

        mVehicleModulesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mPresenter.bindView(this);
        mPresenter.loadDetailVehicleInfo(getArguments().getInt(VEHICLE_ID_ARG));

        OnItemClickListener<VehicleModule> listener = mPresenter::onVehicleModuleClick;
        mTurretModuleButton.setOnItemClickListener(listener);
        mGunModuleButton.setOnItemClickListener(listener);
        mSuspensionModuleButton.setOnItemClickListener(listener);
        mEngineModuleButton.setOnItemClickListener(listener);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        mPresenter.unbindView();
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showLoading() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mVehicleInfoContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mLoadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showInfoContainer() {
        mVehicleInfoContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadVehicleImage(String imageUrl) {
        mPicasso.load(imageUrl)
                .into(mVehicleImageView);
    }

    @Override
    public void setVehicleName(String name) {
        mCollapsingToolbarLayout.setTitle(name);
    }

    @Override
    public void setVehicleDescription(String description) {
        mVehicleDescription.setVisibility(View.VISIBLE);
        mVehicleDescription.setText(description);
    }

    @Override
    public void setVehicleNation(String nation) {
        mVehicleNationTextView.setText(nation);
    }

    @Override
    public void setVehicleType(String type) {
        mVehicleTypeTextView.setText(type);
    }

    @Override
    public void setVehicleTier(String tier) {
        mVehicleTierTextView.setText(tier);
    }

    @Override
    public void showNextTanks(List<ShortVehicleInfoUIModel> models) {
        mNextTanksContainer.setVisibility(View.VISIBLE);
        mNextTanksAdapter.setItems(models);
    }

    @Override
    public void showNextTankViewScreen(int id) {
        showFragment(ViewVehicleFragment.newInstance(id));
    }

    @Override
    public void setPercentageVehicleInfo(String firepower,
                                         String maneuverability,
                                         String protection,
                                         String shotEfficiency) {
        mFirePowerTextView.setText(getString(R.string.percent_mask, firepower));
        mManeuverabilityTextView.setText(getString(R.string.percent_mask, maneuverability));
        mProtectionTextView.setText(getString(R.string.percent_mask, protection));
        mShotEfficiencyTextView.setText(getString(R.string.percent_mask, shotEfficiency));
    }

    @Override
    public void setHullArmorInfo(ArmorDataModel hullArmorDataModel) {
        mArmorHullFrontTextView.setText(getString(R.string.armor_mask, hullArmorDataModel.getFront()));
        mArmorHullRearTextView.setText(getString(R.string.armor_mask, hullArmorDataModel.getRear()));
        mArmorHullSidesTextView.setText(getString(R.string.armor_mask, hullArmorDataModel.getSides()));
    }

    @Override
    public void setTurretArmorInfo(ArmorDataModel turretArmorDataModel) {
        mArmorTurretFrontTextView.setText(getString(R.string.armor_mask, turretArmorDataModel.getFront()));
        mArmorTurretRearTextView.setText(getString(R.string.armor_mask, turretArmorDataModel.getRear()));
        mArmorTurretSidesTextView.setText(getString(R.string.armor_mask, turretArmorDataModel.getSides()));
    }

    @Override
    public void setShowPremiumIcon(boolean show) {
        mPremiumImageView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setModules(VehicleModule turret,
                           VehicleModule gun,
                           VehicleModule suspension,
                           VehicleModule engine) {
        TransitionManager.beginDelayedTransition(mModulesContainer, new Fade(Fade.IN));
        mModulesContainer.setVisibility(View.VISIBLE);
        mModulesContainerShadow.setVisibility(View.VISIBLE);
        mTurretModuleButton.setVehicleModule(turret);
        mGunModuleButton.setVehicleModule(gun);
        mSuspensionModuleButton.setVehicleModule(suspension);
        mEngineModuleButton.setVehicleModule(engine);
    }

    @Override
    public void showVehicleModules(List<? extends VehicleModule> modules) {
        TransitionManager.beginDelayedTransition(mVehicleModulesRecyclerView, new Slide());
        mVehicleModulesRecyclerView.setVisibility(View.VISIBLE);
        mVehicleModulesAdapter = new VehicleModulesAdapter(getContext(), mPicasso);
        mVehicleModulesRecyclerView.setAdapter(mVehicleModulesAdapter);
        mVehicleModulesAdapter.setItems(modules);
    }

    @Override
    public void hideVehicleModules() {
        TransitionManager.beginDelayedTransition(mVehicleModulesRecyclerView, new Fade(Fade.IN));
        mVehicleModulesRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void setVehicleTypeImage(int typeDrawable) {
        mVehicleTypeImageView.setImageResource(typeDrawable);
    }

    @Override
    public void onItemClick(ShortVehicleInfoUIModel item) {
        mPresenter.onNextVehicleClick(item);
    }
}
