package ru.kizup.wotblitzhelper.presentation.view.vehicles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.kizup.wotblitzhelper.base.BaseView;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleSection;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IVehiclesView extends BaseView {

    void showLoading();

    void hideLoading();

    void setItems(Map<ShortVehicleSection, List<ShortVehicleInfoUIModel>> items);

    void showDetailVehicleScreen(int id);
}
