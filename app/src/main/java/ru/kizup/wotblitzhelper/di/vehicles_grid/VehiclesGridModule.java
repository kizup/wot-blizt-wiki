package ru.kizup.wotblitzhelper.di.vehicles_grid;

import dagger.Module;
import dagger.Provides;
import ru.kizup.wotblitzhelper.business.VehiclesMapper;
import ru.kizup.wotblitzhelper.business.vehicles_grid.IVehiclesGridInteractor;
import ru.kizup.wotblitzhelper.business.vehicles_grid.VehiclesGridInteractor;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.IVehiclesRepository;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.VehiclesRepository;
import ru.kizup.wotblitzhelper.presentation.presenter.vehicles_grid.IVehiclesGridPresenter;
import ru.kizup.wotblitzhelper.presentation.presenter.vehicles_grid.VehiclesGridPresenter;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class VehiclesGridModule {

    @Provides
    @VehiclesGridScope
    IVehiclesGridPresenter provideVehiclesGridPresenter(IVehiclesGridInteractor interactor,
                                                    RxSchedulersAbs rxSchedulersAbs) {
        return new VehiclesGridPresenter(interactor, rxSchedulersAbs);
    }

    @Provides
    @VehiclesGridScope
    IVehiclesGridInteractor provideVehiclesGridInteractor(IVehiclesRepository repository,
                                                          VehiclesMapper mapper) {
        return new VehiclesGridInteractor(repository, mapper);
    }

    @Provides
    @VehiclesGridScope
    IVehiclesRepository providesVehiclesRepository(IApiService apiService,
                                                   IDatabaseHelper helper) {
        return new VehiclesRepository(apiService, helper);
    }

}
