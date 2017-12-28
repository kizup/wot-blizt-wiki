package ru.kizup.wotblitzhelper.di.common_info;

import dagger.Subcomponent;
import ru.kizup.wotblitzhelper.presentation.view.common_info.CommonInfoFragment;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Subcomponent(modules = {
        CommonInfoModule.class
})
@CommonInfoScope
public interface CommonInfoComponent {

    void inject(CommonInfoFragment fragment);

}
