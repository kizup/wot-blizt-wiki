package ru.kizup.wotblitzhelper.business.vehicles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.IVehiclesRepository;
import ru.kizup.wotblitzhelper.models.common_info.VehicleNationUIModel;
import ru.kizup.wotblitzhelper.models.common_info.VehicleTypeUIModel;
import ru.kizup.wotblitzhelper.models.vehicles.FilterType;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleSection;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesInteractor implements IVehiclesInteractor {

    private IVehiclesRepository mVehiclesRepository;
    private FilterType mFilterType;

    public VehiclesInteractor(IVehiclesRepository vehiclesRepository) {
        mVehiclesRepository = vehiclesRepository;
    }

    @Override
    public Single<List<ShortVehicleInfoUIModel>> getAllVehicles() {
        return mVehiclesRepository.getAllVehiclesFromDatabase()
                .flatMap(vehicles -> {
                    if (vehicles.isEmpty()) return getVehiclesFromServerAndCache();
                    return Single.just(vehicles);
                })
                .flatMapObservable(Observable::fromIterable)
                .map(this::mapToUIModel)
                .toList();
    }

    private Single<List<ShortVehicleInfoDataModel>> getVehiclesFromServerAndCache() {
        return mVehiclesRepository.getAllVehiclesFromServer()
                .map(Response::body)
                .map(response -> {
                    if (!response.isSuccess()) {
                        throw new FailureResponseException(response.getError());
                    }
                    return response;
                })
                .map(BaseResponse::getData)
                .flatMapObservable(vehiclesMap -> Observable.fromIterable(vehiclesMap.values()))
                .doOnNext(shortDataModel -> mVehiclesRepository.saveModel(shortDataModel).subscribe())
                .toList();
    }

    @Override
    public Single<Map<ShortVehicleSection, List<ShortVehicleInfoUIModel>>> getFilteredVehicles(FilterType filterType) {
        mFilterType = filterType;
        return getVehiclesFromRepository()
                .map(hashMap -> Collections.synchronizedSortedMap(new TreeMap<>(hashMap)));
    }

    private Single<HashMap<ShortVehicleSection, List<ShortVehicleInfoUIModel>>> getVehiclesFromRepository() {
        HashMap<ShortVehicleSection, List<ShortVehicleInfoUIModel>> models = new HashMap<>();
        return mVehiclesRepository.getAllVehiclesFromDatabase()
                .flatMap(vehicles -> {
                    if (vehicles.isEmpty()) return getVehiclesFromServerAndCache();
                    return Single.just(vehicles);
                })
                .flatMapObservable(Observable::fromIterable)
                .doOnNext(dataModel -> {
                    ShortVehicleSection key = getSection(dataModel);
                    if (!models.containsKey(key)) {
                        models.put(key, new ArrayList<>());
                    }

                    models.get(key).add(mapToUIModel(dataModel));
                })
                .toSortedList((o1, o2) -> o1.getTier().compareTo(o2.getTier()))
                .map(list -> models);
    }

    private ShortVehicleSection getSection(ShortVehicleInfoDataModel dataModel) {
        switch (mFilterType) {
            case BY_NATION: {
                String nation = dataModel.getNation();
                VehicleNationUIModel model = new VehicleNationUIModel(nation,
                        mVehiclesRepository.getVehicleNationNameByCode(nation).blockingGet());
                return new ShortVehicleSection(model);
            }
            case BY_TYPE: {
                String type = dataModel.getType();
                VehicleTypeUIModel model = new VehicleTypeUIModel(type,
                        mVehiclesRepository.getVehicleTypeNameByCode(type).blockingGet());
                return new ShortVehicleSection(model);
            }
            case BY_TIER: {
                return new ShortVehicleSection(dataModel.getTier());
            }
        }
        return null;
    }

    private ShortVehicleInfoUIModel mapToUIModel(ShortVehicleInfoDataModel dataModel) {
        String cost = dataModel.getCost() == null
                ? String.valueOf(0)
                : (dataModel.getCost().getPriceCredit() != null
                ? String.valueOf(dataModel.getCost().getPriceCredit())
                : String.valueOf(dataModel.getCost().getPriceGold()));
        boolean isPremium = dataModel.getCost() != null && dataModel.getCost().getPriceGold() != null;
        return new ShortVehicleInfoUIModel(
                dataModel.getTankId(),
                dataModel.getName(),
                dataModel.getDescription(),
                String.valueOf(dataModel.getTier()),
                dataModel.getImages().getPreview(),
                cost,
                isPremium
        );
    }

}
