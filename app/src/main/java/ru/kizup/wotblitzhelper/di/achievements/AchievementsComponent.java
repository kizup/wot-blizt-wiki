package ru.kizup.wotblitzhelper.di.achievements;

import dagger.Subcomponent;
import ru.kizup.wotblitzhelper.presentation.view.achievements.AchievementsFragment;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Subcomponent(modules = {
        AchievementsModule.class
})
@AchievementsScope
public interface AchievementsComponent {

    void inject(AchievementsFragment fragment);

}
