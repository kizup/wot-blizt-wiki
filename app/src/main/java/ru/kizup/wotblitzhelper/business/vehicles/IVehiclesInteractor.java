package ru.kizup.wotblitzhelper.business.vehicles;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.vehicles.FilterType;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleSection;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IVehiclesInteractor {

    Single<List<ShortVehicleInfoUIModel>> getAllVehicles();

    Single<Map<ShortVehicleSection, List<ShortVehicleInfoUIModel>>> getFilteredVehicles(FilterType filterType);

}
