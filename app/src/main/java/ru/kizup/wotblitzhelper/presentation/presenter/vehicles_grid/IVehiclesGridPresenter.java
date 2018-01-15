package ru.kizup.wotblitzhelper.presentation.presenter.vehicles_grid;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.presentation.view.vehicles_grid.IVehiclesGridView;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IVehiclesGridPresenter extends BasePresenter<IVehiclesGridView> {

    void loadVehicles();

    void onVehicleClick(ShortVehicleInfoUIModel model);

}
