package ru.kizup.wotblitzhelper.presentation.view.vehicles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.HelperApp;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.base.BaseFragment;
import ru.kizup.wotblitzhelper.di.vehicles.VehiclesModule;
import ru.kizup.wotblitzhelper.models.vehicles.FilterType;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleSection;
import ru.kizup.wotblitzhelper.presentation.presenter.vehicles.IVehiclesPresenter;
import ru.kizup.wotblitzhelper.presentation.view.view_vehicle.ViewVehicleFragment;
import ru.kizup.wotblitzhelper.utils.listeners.OnItemClickListener;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesFragment extends BaseFragment
        implements IVehiclesView,
        OnItemClickListener<ShortVehicleInfoUIModel> {

    @Inject
    IVehiclesPresenter mPresenter;

    @BindView(R.id.loading)
    ProgressBar mLoadingIndicator;
    @BindView(R.id.rv_vehicles)
    RecyclerView mVehiclesRecyclerView;

    private Unbinder mUnbinder;
    private VehiclesAdapter mVehiclesAdapter;

    public static VehiclesFragment newInstance() {
        Bundle args = new Bundle();
        VehiclesFragment fragment = new VehiclesFragment();
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
                .with(new VehiclesModule())
                .inject(this);

        mVehiclesAdapter = new VehiclesAdapter(getContext(), this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_vehicles;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_vehicles_fragment, menu);
//        menu.findItem(R.id.action_filter_by_tier).setChecked(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_filter: {
//                mPresenter.onFilterClick();
//                break;
//            }
            case R.id.action_filter_by_nation: {
                mPresenter.changeFilterType(FilterType.BY_NATION);
                return true;
            }
            case R.id.action_filter_by_tier: {
                mPresenter.changeFilterType(FilterType.BY_TIER);
                return true;
            }
            case R.id.action_filter_by_type: {
                mPresenter.changeFilterType(FilterType.BY_TYPE);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mVehiclesAdapter.setLayoutManager(gridLayoutManager);
        mVehiclesRecyclerView.setLayoutManager(gridLayoutManager);
        mVehiclesRecyclerView.setAdapter(mVehiclesAdapter);

        mPresenter.bindView(this);
        mPresenter.loadAllVehicles();
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
        mVehiclesRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mLoadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void setItems(Map<ShortVehicleSection, List<ShortVehicleInfoUIModel>> items) {
        mVehiclesRecyclerView.setVisibility(View.VISIBLE);
        mVehiclesAdapter.setItems(items);
    }

    @Override
    public void showDetailVehicleScreen(int id) {
        showFragment(ViewVehicleFragment.newInstance(id));
    }

    @Override
    public void onItemClick(ShortVehicleInfoUIModel item) {
        mPresenter.onVehicleClick(item);
    }
}
