package ru.kizup.wotblitzhelper.presentation.view.vehicles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.HelperApp;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.base.BaseFragment;
import ru.kizup.wotblitzhelper.di.vehicles.VehiclesModule;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.presentation.presenter.vehicles.IVehiclesPresenter;
import ru.kizup.wotblitzhelper.presentation.view.view_vehicle.ViewVehicleFragment;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 11.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesFragment extends BaseFragment
        implements IVehiclesView,
        OnItemClickListener<ShortVehicleInfoUIModel> {

    private static final String VEHICLE_NATION_ARG = "vehicle_nation_arg";
    private static final String VEHICLE_TYPE_ARG = "vehicle_type_arg";

    @Inject
    IVehiclesPresenter mPresenter;
    @Inject
    Picasso mPicasso;

    @BindView(R.id.loading)
    ProgressBar mLoadingIndicator;
    @BindView(R.id.rv_vehicles)
    RecyclerView mVehiclesRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Unbinder mUnbinder;
    private VehiclesAdapter mVehiclesAdapter;

    public static VehiclesFragment newInstance(String nation, String type) {
        Bundle args = new Bundle();
        args.putString(VEHICLE_NATION_ARG, nation);
        args.putString(VEHICLE_TYPE_ARG, type);
        VehiclesFragment fragment = new VehiclesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        HelperApp.get(getContext())
                .getAppComponent()
                .with(new VehiclesModule())
                .inject(this);

        mVehiclesAdapter = new VehiclesAdapter(getContext(),
                this,
                mPicasso);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_vehicles;
    }

    @Override
    public void onPostViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);

        setToolbar(mToolbar);
        setShowBackArrow();

        mVehiclesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                getResources().getInteger(R.integer.vehicles_span_count)));
        mVehiclesRecyclerView.setAdapter(mVehiclesAdapter);

        mPresenter.bindView(this);
        mPresenter.loadVehicles(getArguments().getString(VEHICLE_NATION_ARG),
                getArguments().getString(VEHICLE_TYPE_ARG));
    }

    @Override
    public void onDestroyView() {
        mPresenter.unbindView();
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showLoadedVehicles(List<ShortVehicleInfoUIModel> models) {
        mLoadingIndicator.setVisibility(View.GONE);
        mVehiclesRecyclerView.setVisibility(View.VISIBLE);
        mVehiclesAdapter.setItems(models);
    }

    @Override
    public void showViewDetailVehicleScreen(int vehicleId) {
        showFragment(ViewVehicleFragment.newInstance(vehicleId));
    }

    @Override
    public void onItemClick(ShortVehicleInfoUIModel item) {
        mPresenter.clickOnVehicle(item);
    }
}
