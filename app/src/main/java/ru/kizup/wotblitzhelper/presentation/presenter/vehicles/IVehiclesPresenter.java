package ru.kizup.wotblitzhelper.presentation.presenter.vehicles;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.models.vehicles.FilterType;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.presentation.view.vehicles.IVehiclesView;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IVehiclesPresenter extends BasePresenter<IVehiclesView> {

    void loadAllVehicles();

    void changeFilterType(FilterType filterType);

    void onFilterClick();

    void onVehicleClick(ShortVehicleInfoUIModel model);

}
