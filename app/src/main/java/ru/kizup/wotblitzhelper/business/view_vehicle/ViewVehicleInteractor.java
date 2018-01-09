package ru.kizup.wotblitzhelper.business.view_vehicle;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.IVehiclesRepository;
import ru.kizup.wotblitzhelper.models.view_vehicle.ViewVehicleDetailUIModel;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ViewVehicleInteractor
        implements IViewVehicleInteractor {

    private IVehiclesRepository mVehiclesRepository;

    public ViewVehicleInteractor(IVehiclesRepository vehiclesRepository) {
        mVehiclesRepository = vehiclesRepository;
    }

    @Override
    public Single<ViewVehicleDetailUIModel> getVehicleDetailInformation(int id) {
        return mVehiclesRepository.getDetailVehicleInfo(id)
                .map(detailVehicleDataModel -> {
                    System.out.println();
                    return new ViewVehicleDetailUIModel();
                });
    }
}
