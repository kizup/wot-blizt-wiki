package ru.kizup.wotblitzhelper.presentation.presenter.vehicles_grid;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.kizup.wotblitzhelper.business.vehicles_grid.IVehiclesGridInteractor;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.presentation.view.vehicles_grid.IVehiclesGridView;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesGridPresenter implements IVehiclesGridPresenter {

    private IVehiclesGridView mVehiclesView;
    private IVehiclesGridInteractor mVehiclesInteractor;
    private RxSchedulersAbs mRxSchedulersAbs;
    private CompositeDisposable mCompositeDisposable;

    public VehiclesGridPresenter(IVehiclesGridInteractor vehiclesInteractor,
                                 RxSchedulersAbs rxSchedulersAbs) {
        mVehiclesInteractor = vehiclesInteractor;
        mRxSchedulersAbs = rxSchedulersAbs;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void bindView(IVehiclesGridView view) {
        mVehiclesView = view;
    }

    @Override
    public void unbindView() {
        if (mCompositeDisposable != null) mCompositeDisposable.clear();
        mVehiclesView = null;
    }

    @Override
    public void loadVehicles() {
        loadAllVehiclesFromData();
    }

    @Override
    public void onVehicleClick(ShortVehicleInfoUIModel model) {
        mVehiclesView.showVehiclesByNationAndTypeScreen(model.getNationCode(), model.getTypeCode());
    }

    private void loadAllVehiclesFromData() {
        mVehiclesView.showLoading();
        mCompositeDisposable.add(mVehiclesInteractor.getSingleTypeAndNationVehicles()
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessLoadedVehicles,
                        this::handleErrorLoadVehicles));
    }

    private void setItemsToView(List<ShortVehicleInfoUIModel> items) {
        mVehiclesView.hideLoading();
        mVehiclesView.setItems(items);
    }

    private void handleSuccessLoadedVehicles(List<ShortVehicleInfoUIModel> models) {
        mVehiclesView.hideLoading();
        setItemsToView(models);
    }

    private void handleErrorLoadVehicles(Throwable throwable) {
        throwable.printStackTrace();
        mVehiclesView.hideLoading();
    }

}
