package ru.kizup.wotblitzhelper.presentation.presenter.view_vehicle;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.VehicleModule;
import ru.kizup.wotblitzhelper.presentation.view.view_vehicle.IViewVehicleView;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IViewVehiclePresenter extends BasePresenter<IViewVehicleView> {

    void loadDetailVehicleInfo(int vehicleId);

    void onNextVehicleClick(ShortVehicleInfoUIModel item);

    void onVehicleModuleClick(VehicleModule module);

    void onVehicleInfoContainerClick();
}
