package ru.kizup.wotblitzhelper.presentation.view.vehicles;

import java.util.List;

import ru.kizup.wotblitzhelper.base.BaseView;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;

/**
 * Created by: dpuzikov on 11.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IVehiclesView extends BaseView {

    void showLoadedVehicles(List<ShortVehicleInfoUIModel> models);

    void showViewDetailVehicleScreen(int vehicleId);

}
