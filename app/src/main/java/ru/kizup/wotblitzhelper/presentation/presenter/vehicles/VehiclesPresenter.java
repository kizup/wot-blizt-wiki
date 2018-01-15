package ru.kizup.wotblitzhelper.presentation.presenter.vehicles;

import android.content.Context;
import android.content.res.Configuration;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.business.vehicles.IVehiclesInteractor;
import ru.kizup.wotblitzhelper.data.db.EntityNotFoundException;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.presentation.view.vehicles.IVehiclesView;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 11.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesPresenter implements IVehiclesPresenter {

    private IVehiclesInteractor mVehiclesInteractor;
    private RxSchedulersAbs mRxSchedulersAbs;
    private CompositeDisposable mCompositeDisposable;
    private IVehiclesView mVehiclesView;
    private Context mContext;

    public VehiclesPresenter(IVehiclesInteractor vehiclesInteractor,
                             RxSchedulersAbs rxSchedulersAbs,
                             Context context) {
        mVehiclesInteractor = vehiclesInteractor;
        mRxSchedulersAbs = rxSchedulersAbs;
        mContext = context;
        mCompositeDisposable = new CompositeDisposable();
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
    public void loadVehicles(String nation, String code) {
        Disposable disposable = mVehiclesInteractor.getVehiclesByNationAndCode(nation, code)
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessLoadedVehicles, this::handleErrorLoadVehicles);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void clickOnVehicle(ShortVehicleInfoUIModel item) {
        mVehiclesView.showViewDetailVehicleScreen(item.getId());
    }

    private void handleSuccessLoadedVehicles(List<ShortVehicleInfoUIModel> models) {
        mVehiclesView.showLoadedVehicles(models);
    }

    private void handleErrorLoadVehicles(Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof EntityNotFoundException) {
            mVehiclesView.showMessage(mContext.getString(R.string.error_entity_not_found));
        } else {
            mVehiclesView.showMessage(mContext.getString(R.string.unknown_error));
        }
    }

}
