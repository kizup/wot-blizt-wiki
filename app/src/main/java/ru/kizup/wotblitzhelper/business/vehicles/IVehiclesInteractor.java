package ru.kizup.wotblitzhelper.business.vehicles;

import java.util.List;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;

/**
 * Created by: dpuzikov on 11.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IVehiclesInteractor {

    Single<List<ShortVehicleInfoUIModel>> getVehiclesByNationAndCode(String nation, String code);

}
