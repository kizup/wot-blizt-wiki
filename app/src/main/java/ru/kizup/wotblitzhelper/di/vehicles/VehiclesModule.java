package ru.kizup.wotblitzhelper.di.vehicles;

import dagger.Module;
import dagger.Provides;
import ru.kizup.wotblitzhelper.business.vehicles.IVehiclesInteractor;
import ru.kizup.wotblitzhelper.business.vehicles.VehiclesInteractor;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.IVehiclesRepository;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.VehiclesRepository;
import ru.kizup.wotblitzhelper.presentation.presenter.vehicles.IVehiclesPresenter;
import ru.kizup.wotblitzhelper.presentation.presenter.vehicles.VehiclesPresenter;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class VehiclesModule {

    @Provides
    @VehiclesScope
    IVehiclesPresenter provideVehiclesPresenter(IVehiclesInteractor interactor,
                                                RxSchedulersAbs rxSchedulersAbs) {
        return new VehiclesPresenter(interactor, rxSchedulersAbs);
    }

    @Provides
    @VehiclesScope
    IVehiclesInteractor provideVehiclesInteractor(IVehiclesRepository repository) {
        return new VehiclesInteractor(repository);
    }

    @Provides
    @VehiclesScope
    IVehiclesRepository providesVehiclesRepository(IApiService apiService,
                                                   IDatabaseHelper helper) {
        return new VehiclesRepository(apiService, helper);
    }

}
