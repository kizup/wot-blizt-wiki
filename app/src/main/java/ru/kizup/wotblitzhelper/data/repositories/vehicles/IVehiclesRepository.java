package ru.kizup.wotblitzhelper.data.repositories.vehicles;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.realm.RealmObject;
import retrofit2.Response;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.EngineDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.GunDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModulesDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.SuspensionDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.TurretDataModel;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IVehiclesRepository {

    Single<String> getVehicleTypeNameByCode(String code);

    Single<String> getVehicleNationNameByCode(String code);

//    Single<DetailVehicleDataModel> getDetailVehicleInfo(int id);

    Observable<BaseResponse<HashMap<String, DetailVehicleDataModel>>> getDetailVehicleInfoFromServer(int vehicleId);

    Single<DetailVehicleDataModel> getDetailVehicleInfoFromDatabase(int vehicleId);

    Single<List<ShortVehicleInfoDataModel>> getNextVehiclesShortInfo(Integer[] ids);

    Single<List<TurretDataModel>> getTurretsForVehicleFromDatabase(int vehicleId);

    Single<List<GunDataModel>> getGunsForVehicleFromDatabase(int vehicleId);

    Single<List<SuspensionDataModel>> getSuspensionsForVehicleFromDatabase(int vehicleId);

    Single<List<EngineDataModel>> getEnginesForVehicleFromDatabase(int vehicleId);

    Single<Response<BaseResponse<ModulesDataModel>>> getModulesVehicleFromServer(Integer[] ids, String type);

    Single<List<ShortVehicleInfoDataModel>> getAllVehiclesFromDatabase();

    Single<Response<BaseResponse<HashMap<String, ShortVehicleInfoDataModel>>>> getAllVehiclesFromServer();

    Single<Boolean> saveAllVehiclesInDatabase(List<ShortVehicleInfoDataModel> vehicles);

    Single<Boolean> saveModel(RealmObject object);

}
