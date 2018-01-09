package ru.kizup.wotblitzhelper.presentation.presenter.view_vehicle;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.presentation.view.view_vehicle.IViewVehicleView;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IViewVehiclePresenter extends BasePresenter<IViewVehicleView> {

    void loadDetailVehicleInfo(int vehicleId);

}
