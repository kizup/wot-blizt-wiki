package ru.kizup.wotblitzhelper.di.vehicles;

import dagger.Subcomponent;
import ru.kizup.wotblitzhelper.presentation.view.vehicles.VehiclesFragment;

/**
 * Created by: dpuzikov on 12.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Subcomponent(modules = {
        VehiclesModule.class
})
@VehiclesScope
public interface VehiclesComponent {

    void inject(VehiclesFragment fragment);

}
