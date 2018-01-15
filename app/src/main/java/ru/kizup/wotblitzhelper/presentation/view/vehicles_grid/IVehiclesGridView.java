package ru.kizup.wotblitzhelper.presentation.view.vehicles_grid;

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

public interface IVehiclesGridView extends BaseView {

    void showLoading();

    void hideLoading();

    void setItems(List<ShortVehicleInfoUIModel> items);

    void showVehiclesByNationAndTypeScreen(String nation, String type);
}
