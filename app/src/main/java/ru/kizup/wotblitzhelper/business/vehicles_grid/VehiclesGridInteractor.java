package ru.kizup.wotblitzhelper.business.vehicles_grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.business.VehiclesMapper;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.IVehiclesRepository;
import ru.kizup.wotblitzhelper.models.common_info.VehicleNationUIModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleSection;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesGridInteractor implements IVehiclesGridInteractor {

    private IVehiclesRepository mVehiclesRepository;
    private VehiclesMapper mVehiclesMapper;

    public VehiclesGridInteractor(IVehiclesRepository vehiclesRepository, VehiclesMapper mapper) {
        mVehiclesRepository = vehiclesRepository;
        mVehiclesMapper = mapper;
    }

    @Override
    public Single<List<ShortVehicleInfoUIModel>> getSingleTypeAndNationVehicles() {
        return Single.fromCallable(this::loadVehiclesForGrid)
                .flatMapObservable(Observable::fromIterable)
                .map(mVehiclesMapper::toShortUIModel)
                .toList()
                .doOnSuccess(this::fillHeaders);
    }

    private void fillHeaders(List<ShortVehicleInfoUIModel> models) {
        List<ShortVehicleInfoUIModel> headers = new ArrayList<>(4);

        ShortVehicleInfoUIModel header = ShortVehicleInfoUIModel.header();
        header.setTypeDrawable(R.drawable.ic_at_tank);

        headers.add(header);

        header = ShortVehicleInfoUIModel.header();
        header.setTypeDrawable(R.drawable.ic_heavy_tank);

        headers.add(header);

        header = ShortVehicleInfoUIModel.header();
        header.setTypeDrawable(R.drawable.ic_light_tank);

        headers.add(header);

        header = ShortVehicleInfoUIModel.header();
        header.setTypeDrawable(R.drawable.ic_medium_tank);

        headers.add(header);

        models.addAll(0, headers);
    }

    private List<ShortVehicleInfoDataModel> loadVehiclesForGrid() {
        HashMap<String, String> nationsMap = mVehiclesRepository.getAllVehicleNations()
                .blockingGet();
        HashMap<String, String> typesMap = mVehiclesRepository.getAllVehicleTypes()
                .blockingGet();
        List<String> nationsCodes = new ArrayList<>(nationsMap.keySet());
        List<String> typesCodes = new ArrayList<>(typesMap.keySet());

        Collections.sort(nationsCodes);
        Collections.sort(typesCodes);

        List<ShortVehicleInfoDataModel> models = new ArrayList<>();
        for (String nation : nationsCodes) {
            for (String type : typesCodes) {
                ShortVehicleInfoDataModel model = mVehiclesRepository.getVehicleFromDatabase(nation, type)
                        .blockingGet();
                models.add(model);
            }
        }

        return models;
    }
}
