package ru.kizup.wotblitzhelper.business.view_vehicle;

import java.util.List;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModuleUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.NextTank;
import ru.kizup.wotblitzhelper.models.view_vehicle.VehicleModule;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IViewVehicleInteractor {

    Single<DetailVehicleUIModel> getVehicleDetailInformation(int id);

    Single<List<ShortVehicleInfoUIModel>> getNextTanksShortInfo(List<NextTank> nextTanks);

    Single<List<? extends VehicleModule>> getVehicleModulesByType(VehicleModule.Type type, int vehicleId);

}
