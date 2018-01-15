package ru.kizup.wotblitzhelper.presentation.presenter.vehicles;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.presentation.view.vehicles.IVehiclesView;

/**
 * Created by: dpuzikov on 11.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IVehiclesPresenter extends BasePresenter<IVehiclesView> {

    void loadVehicles(String nation, String code);

    void clickOnVehicle(ShortVehicleInfoUIModel item);
}
