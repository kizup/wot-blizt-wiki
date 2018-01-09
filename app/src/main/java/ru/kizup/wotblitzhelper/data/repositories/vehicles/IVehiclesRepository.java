package ru.kizup.wotblitzhelper.data.repositories.vehicles;

import java.util.List;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleDataModel;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IVehiclesRepository {

    Single<List<ShortVehicleInfoDataModel>> getAllVehicles();

    Single<String> getVehicleTypeNameByCode(String code);

    Single<String> getVehicleNationNameByCode(String code);

    Single<DetailVehicleDataModel> getDetailVehicleInfo(int id);

}
