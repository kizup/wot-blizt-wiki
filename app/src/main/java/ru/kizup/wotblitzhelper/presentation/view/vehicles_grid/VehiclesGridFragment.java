package ru.kizup.wotblitzhelper.presentation.view.vehicles_grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
import ru.kizup.wotblitzhelper.di.vehicles_grid.VehiclesGridModule;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleSection;
import ru.kizup.wotblitzhelper.presentation.presenter.vehicles_grid.IVehiclesGridPresenter;
import ru.kizup.wotblitzhelper.presentation.view.vehicles.VehiclesFragment;
import ru.kizup.wotblitzhelper.presentation.view.view_vehicle.ViewVehicleFragment;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesGridFragment extends BaseFragment
        implements IVehiclesGridView,
        OnItemClickListener<ShortVehicleInfoUIModel> {

    @Inject
    IVehiclesGridPresenter mPresenter;
    @Inject
    Picasso mPicasso;

    @BindView(R.id.loading)
    ProgressBar mLoadingIndicator;
    @BindView(R.id.rv_vehicles)
    RecyclerView mVehiclesGridRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Unbinder mUnbinder;
    private VehiclesGridAdapter mVehiclesGridAdapter;

    public static VehiclesGridFragment newInstance() {
        Bundle args = new Bundle();
        VehiclesGridFragment fragment = new VehiclesGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        HelperApp.get(getContext())
                .getAppComponent()
                .with(new VehiclesGridModule())
                .inject(this);

        mVehiclesGridAdapter = new VehiclesGridAdapter(getContext(), this, mPicasso);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_vehicles_grid;
    }

    @Override
    public void onPostViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);

        setToolbar(mToolbar);
        setShowBackArrow();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        mVehiclesGridRecyclerView.setLayoutManager(gridLayoutManager);
        mVehiclesGridRecyclerView.setAdapter(mVehiclesGridAdapter);
        mVehiclesGridRecyclerView.setNestedScrollingEnabled(false);

        mPresenter.bindView(this);
        mPresenter.loadVehicles();
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
        mVehiclesGridRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mLoadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void setItems(List<ShortVehicleInfoUIModel> items) {
        mVehiclesGridRecyclerView.setVisibility(View.VISIBLE);
        mVehiclesGridAdapter.setItems(items);
    }

    @Override
    public void showVehiclesByNationAndTypeScreen(String nation, String type) {
        showFragment(VehiclesFragment.newInstance(nation, type));
    }

    @Override
    public void onItemClick(ShortVehicleInfoUIModel item) {
        mPresenter.onVehicleClick(item);
    }
}
