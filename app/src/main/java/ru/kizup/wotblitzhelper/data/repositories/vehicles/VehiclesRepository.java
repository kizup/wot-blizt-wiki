package ru.kizup.wotblitzhelper.data.repositories.vehicles;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.Repository;
import ru.kizup.wotblitzhelper.models.common_info.VehicleTypeDao;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleDataModel;

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
    public Single<List<ShortVehicleInfoDataModel>> getAllVehicles() {
        return getDatabaseHelper().getAllVehicles()
                .flatMap(models -> {
                    if (models.isEmpty()) return getAllVehiclesFromServer();
                    return Single.just(models);
                });
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
    public Single<DetailVehicleDataModel> getDetailVehicleInfo(int id) {
        return getDatabaseHelper().getDetailVehicleInfo(id)
                .flatMap(model -> {
                    if (model.getTankId() == null) return getDetailVehicleInfoFromServer(id);
                    return Single.just(model);
                });
    }

    private Single<DetailVehicleDataModel> getDetailVehicleInfoFromServer(int id) {
        return getApiService().getDetailVehicleInfo(id)
                .map(response -> {
                    if (!response.isSuccess()) {
                        throw new FailureResponseException(response.getError());
                    }
                    return response;
                })
                .map(BaseResponse::getData)
                .map(map -> map.get(new ArrayList<>(map.keySet()).get(0)))
                .doOnNext(model -> getDatabaseHelper().saveModel(model).subscribe())
                .singleOrError();
    }

    private Single<List<ShortVehicleInfoDataModel>> getAllVehiclesFromServer() {
        return getApiService().getShortAllVehicleInfo("tank_id,description,name,nation,tier,type,cost,images")
                .map(response -> {
                    if (!response.isSuccess()) {
                        throw new FailureResponseException(response.getError());
                    }
                    return response;
                })
                .map(BaseResponse::getData)
                .flatMap(vehiclesMap -> Observable.fromIterable(vehiclesMap.values()))
                .doOnNext(shortDataModel -> getDatabaseHelper().saveModel(shortDataModel).subscribe())
                .toList();
    }

}
