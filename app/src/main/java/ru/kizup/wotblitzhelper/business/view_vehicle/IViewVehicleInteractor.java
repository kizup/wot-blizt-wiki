package ru.kizup.wotblitzhelper.business.view_vehicle;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.view_vehicle.ViewVehicleDetailUIModel;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IViewVehicleInteractor {

    Single<ViewVehicleDetailUIModel> getVehicleDetailInformation(int id);

}
