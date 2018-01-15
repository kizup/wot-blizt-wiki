package ru.kizup.wotblitzhelper.business.vehicles;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.kizup.wotblitzhelper.business.VehiclesMapper;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.IVehiclesRepository;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;

/**
 * Created by: dpuzikov on 11.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesInteractor implements IVehiclesInteractor {

    private IVehiclesRepository mVehiclesRepository;
    private VehiclesMapper mVehiclesMapper;

    public VehiclesInteractor(IVehiclesRepository vehiclesRepository,
                              VehiclesMapper vehiclesMapper) {
        mVehiclesRepository = vehiclesRepository;
        mVehiclesMapper = vehiclesMapper;
    }

    @Override
    public Single<List<ShortVehicleInfoUIModel>> getVehiclesByNationAndCode(String nation, String code) {
        return mVehiclesRepository.getVehiclesFromDatabaseByNationAndType(nation, code)
                .flatMapObservable(Observable::fromIterable)
                .map(mVehiclesMapper::toShortUIModel)
                .toSortedList();
    }
}
