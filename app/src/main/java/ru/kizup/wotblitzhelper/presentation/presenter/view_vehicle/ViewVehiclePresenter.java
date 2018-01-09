package ru.kizup.wotblitzhelper.presentation.presenter.view_vehicle;

import io.reactivex.disposables.CompositeDisposable;
import ru.kizup.wotblitzhelper.business.view_vehicle.IViewVehicleInteractor;
import ru.kizup.wotblitzhelper.presentation.view.view_vehicle.IViewVehicleView;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ViewVehiclePresenter implements IViewVehiclePresenter {

    private IViewVehicleView mVehicleView;
    private RxSchedulersAbs mRxSchedulersAbs;
    private IViewVehicleInteractor mViewVehicleInteractor;
    private CompositeDisposable mCompositeDisposable;
    private int mVehicleId;

    public ViewVehiclePresenter(RxSchedulersAbs rxSchedulersAbs,
                                IViewVehicleInteractor viewVehicleInteractor) {
        mRxSchedulersAbs = rxSchedulersAbs;
        mViewVehicleInteractor = viewVehicleInteractor;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void bindView(IViewVehicleView view) {
        mVehicleView = view;
    }

    @Override
    public void unbindView() {
        if (mCompositeDisposable != null) mCompositeDisposable.clear();
        mVehicleView = null;
    }

    @Override
    public void loadDetailVehicleInfo(int vehicleId) {
        mVehicleId = vehicleId;

        mCompositeDisposable.add(mViewVehicleInteractor.getVehicleDetailInformation(vehicleId)
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe((viewVehicleDetailUIModel, throwable) -> {
                    System.out.println();
                }));
    }
}
