package ru.kizup.wotblitzhelper.presentation.view.view_vehicle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.kizup.wotblitzhelper.HelperApp;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.base.BaseFragment;
import ru.kizup.wotblitzhelper.di.view_vehicle.ViewVehicleModule;
import ru.kizup.wotblitzhelper.presentation.presenter.view_vehicle.IViewVehiclePresenter;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ViewVehicleFragment extends BaseFragment
        implements IViewVehicleView {

    private static final String VEHICLE_ID_ARG = "vehicle_id_arg";

    @Inject
    IViewVehiclePresenter mPresenter;

    private Unbinder mUnbinder;

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
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_view_vehicle;
    }

    @Override
    public void onPostViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);

        mPresenter.bindView(this);
        mPresenter.loadDetailVehicleInfo(getArguments().getInt(VEHICLE_ID_ARG));
    }

    @Override
    public void onDestroyView() {
        mPresenter.unbindView();
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
