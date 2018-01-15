package ru.kizup.wotblitzhelper.di.vehicles;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.kizup.wotblitzhelper.business.VehiclesMapper;
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
 * Created by: dpuzikov on 11.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class VehiclesModule {

    @Provides
    @VehiclesScope
    IVehiclesPresenter provideVehiclesPresenter(IVehiclesInteractor interactor,
                                                RxSchedulersAbs rxSchedulersAbs,
                                                Context context) {
        return new VehiclesPresenter(interactor, rxSchedulersAbs, context);
    }

    @Provides
    @VehiclesScope
    IVehiclesInteractor provideVehiclesInteractor(IVehiclesRepository repository,
                                                  VehiclesMapper mapper) {
        return new VehiclesInteractor(repository, mapper);
    }

    @Provides
    @VehiclesScope
    IVehiclesRepository provideVehiclesRepository(IApiService service,
                                                  IDatabaseHelper helper) {
        return new VehiclesRepository(service, helper);
    }

}
