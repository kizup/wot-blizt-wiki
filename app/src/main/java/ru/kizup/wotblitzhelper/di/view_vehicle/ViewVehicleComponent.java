package ru.kizup.wotblitzhelper.di.view_vehicle;

import dagger.Subcomponent;
import ru.kizup.wotblitzhelper.presentation.view.view_vehicle.ViewVehicleFragment;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Subcomponent(modules = {
        ViewVehicleModule.class
})
@ViewVehicleScope
public interface ViewVehicleComponent {

    void inject(ViewVehicleFragment fragment);

}
