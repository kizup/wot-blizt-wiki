package ru.kizup.wotblitzhelper.di.vehicles_grid;

import dagger.Subcomponent;
import ru.kizup.wotblitzhelper.presentation.view.vehicles_grid.VehiclesGridFragment;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Subcomponent(modules = {
        VehiclesGridModule.class
})
@VehiclesGridScope
public interface VehiclesGridComponent {

    void inject(VehiclesGridFragment fragment);

}
