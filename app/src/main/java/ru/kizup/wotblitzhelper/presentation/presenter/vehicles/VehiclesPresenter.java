package ru.kizup.wotblitzhelper.presentation.presenter.vehicles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.kizup.wotblitzhelper.business.vehicles.IVehiclesInteractor;
import ru.kizup.wotblitzhelper.models.vehicles.FilterType;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleSection;
import ru.kizup.wotblitzhelper.presentation.view.vehicles.IVehiclesView;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesPresenter implements IVehiclesPresenter {

    private IVehiclesView mVehiclesView;
    private IVehiclesInteractor mVehiclesInteractor;
    private RxSchedulersAbs mRxSchedulersAbs;
    private CompositeDisposable mCompositeDisposable;
    private FilterType mFilterType;

    public VehiclesPresenter(IVehiclesInteractor vehiclesInteractor,
                             RxSchedulersAbs rxSchedulersAbs) {
        mVehiclesInteractor = vehiclesInteractor;
        mRxSchedulersAbs = rxSchedulersAbs;
        mCompositeDisposable = new CompositeDisposable();
        mFilterType = FilterType.BY_TIER;
    }

    @Override
    public void bindView(IVehiclesView view) {
        mVehiclesView = view;
    }

    @Override
    public void unbindView() {
        if (mCompositeDisposable != null) mCompositeDisposable.clear();
        mVehiclesView = null;
    }

    @Override
    public void loadAllVehicles() {
        loadAllVehiclesFromData(mFilterType);
    }

    @Override
    public void changeFilterType(FilterType filterType) {
        if (mFilterType == filterType) return;
        mFilterType = filterType;
        loadAllVehiclesFromData(filterType);
    }

    @Override
    public void onFilterClick() {
        // TODO show detailed filter
    }

    @Override
    public void onVehicleClick(ShortVehicleInfoUIModel model) {
        mVehiclesView.showDetailVehicleScreen(model.getId());
    }

    private void loadAllVehiclesFromData(FilterType filterType) {
        mVehiclesView.showLoading();
        Disposable disposable = mVehiclesInteractor.getFilteredVehicles(filterType)
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessLoadedVehicles, this::handleErrorLoadVehicles);
        mCompositeDisposable.add(disposable);
    }

    private void setItemsToView(Map<ShortVehicleSection, List<ShortVehicleInfoUIModel>> items) {
        mVehiclesView.hideLoading();
        mVehiclesView.setItems(items);
    }

    private void handleSuccessLoadedVehicles(Map<ShortVehicleSection, List<ShortVehicleInfoUIModel>> models) {
        mVehiclesView.hideLoading();
        setItemsToView(models);
    }

    private void handleErrorLoadVehicles(Throwable throwable) {
        throwable.printStackTrace();
        mVehiclesView.hideLoading();
    }

}
