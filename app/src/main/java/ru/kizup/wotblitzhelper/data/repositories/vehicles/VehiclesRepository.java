package ru.kizup.wotblitzhelper.data.repositories.vehicles;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.realm.RealmObject;
import retrofit2.Response;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.Repository;
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

public class VehiclesRepository extends Repository
        implements IVehiclesRepository {

    public VehiclesRepository(IApiService apiService, IDatabaseHelper helper) {
        super(apiService, helper);
    }

    @Override
    public Single<String> getVehicleTypeNameByCode(String code) {
        return getDatabaseHelper().getVehicleTypes()
                .flatMap(map -> {
                    if (map.isEmpty()) return super.getAllVehicleTypes();
                    return Single.just(map);
                })
                .flatMap(map -> Single.just(map.get(code)));
    }

    @Override
    public Single<String> getVehicleNationNameByCode(String code) {
        return getDatabaseHelper().getVehicleNations()
                .flatMap(map -> {
                    if (map.isEmpty()) return super.getAllVehicleNations();
                    return Single.just(map);
                })
                .flatMap(map -> Single.just(map.get(code)));
    }

    @Override
    public Single<List<ShortVehicleInfoDataModel>> getNextVehiclesShortInfo(Integer[] ids) {
        return getDatabaseHelper().getVehiclesByIds(ids);
    }

    @Override
    public Single<List<TurretDataModel>> getTurretsForVehicleFromDatabase(int vehicleId) {
        return getDatabaseHelper().getTurretsForVehicle(vehicleId);
    }

    @Override
    public Single<List<GunDataModel>> getGunsForVehicleFromDatabase(int vehicleId) {
        return getDatabaseHelper().getGunsForVehicle(vehicleId);
    }

    @Override
    public Single<List<SuspensionDataModel>> getSuspensionsForVehicleFromDatabase(int vehicleId) {
        return getDatabaseHelper().getSuspensionsForVehicle(vehicleId);
    }

    @Override
    public Single<List<EngineDataModel>> getEnginesForVehicleFromDatabase(int vehicleId) {
        return getDatabaseHelper().getEnginesForVehicle(vehicleId);
    }

    @Override
    public Single<Response<BaseResponse<ModulesDataModel>>> getModulesVehicleFromServer(Integer[] ids, String type) {
        StringBuilder builder = new StringBuilder();
        int idsLength = ids.length - 1;
        for (int i = 0; ; i++) {
            builder.append(ids[i]);
            if (idsLength == i) break;
            builder.append(",");
        }
        return getApiService().getModulesList(builder.toString(), type)
                .singleOrError();
    }

    @Override
    public Single<List<ShortVehicleInfoDataModel>> getAllVehiclesFromDatabase() {
        return getDatabaseHelper().getAllVehicles();
    }

    @Override
    public Single<Response<BaseResponse<HashMap<String, ShortVehicleInfoDataModel>>>> getAllVehiclesFromServer() {
        return getApiService().getShortAllVehicleInfo("tank_id,description,name,nation,tier,type,cost,images")
                .singleOrError();
    }

    @Override
    public Single<Boolean> saveAllVehiclesInDatabase(List<ShortVehicleInfoDataModel> vehicles) {
        return null;
    }

    @Override
    public Single<Boolean> saveModel(RealmObject dataModel) {
        return getDatabaseHelper().saveModel(dataModel);
    }

    @Override
    public Observable<BaseResponse<HashMap<String, DetailVehicleDataModel>>> getDetailVehicleInfoFromServer(int id) {
        return getApiService().getDetailVehicleInfo(id);
    }

    @Override
    public Single<DetailVehicleDataModel> getDetailVehicleInfoFromDatabase(int vehicleId) {
        return getDatabaseHelper().getDetailVehicleInfo(vehicleId);
    }

}
