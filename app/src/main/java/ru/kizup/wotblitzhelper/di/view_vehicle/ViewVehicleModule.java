package ru.kizup.wotblitzhelper.di.view_vehicle;

import dagger.Module;
import dagger.Provides;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.business.view_vehicle.IViewVehicleInteractor;
import ru.kizup.wotblitzhelper.business.view_vehicle.ViewVehicleInteractor;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.IVehiclesRepository;
import ru.kizup.wotblitzhelper.data.repositories.vehicles.VehiclesRepository;
import ru.kizup.wotblitzhelper.di.vehicles.VehiclesScope;
import ru.kizup.wotblitzhelper.presentation.presenter.view_vehicle.IViewVehiclePresenter;
import ru.kizup.wotblitzhelper.presentation.presenter.view_vehicle.ViewVehiclePresenter;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Module
public class ViewVehicleModule {

    @Provides
    @ViewVehicleScope
    IViewVehiclePresenter provideViewVehiclePresenter(RxSchedulersAbs rxSchedulersAbs,
                                                      IViewVehicleInteractor interactor,
                                                      ResponseValidator validator) {
        return new ViewVehiclePresenter(rxSchedulersAbs, interactor, validator);
    }

    @Provides
    @ViewVehicleScope
    IViewVehicleInteractor provideVehiclesInteractor(IVehiclesRepository repository) {
        return new ViewVehicleInteractor(repository);
    }

    @Provides
    @ViewVehicleScope
    IVehiclesRepository providesVehiclesRepository(IApiService apiService,
                                                   IDatabaseHelper helper) {
        return new VehiclesRepository(apiService, helper);
    }

}
