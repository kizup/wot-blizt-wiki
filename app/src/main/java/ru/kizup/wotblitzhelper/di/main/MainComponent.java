package ru.kizup.wotblitzhelper.di.main;

import dagger.Subcomponent;
import ru.kizup.wotblitzhelper.presentation.view.main.MainFragment;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Subcomponent(modules = {
        MainModule.class
})
@MainScope
public interface MainComponent {

    void inject(MainFragment fragment);

}
