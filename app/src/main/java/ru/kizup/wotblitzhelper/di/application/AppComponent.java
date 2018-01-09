package ru.kizup.wotblitzhelper.di.application;

import javax.inject.Singleton;

import dagger.Component;
import ru.kizup.wotblitzhelper.di.achievements.AchievementsComponent;
import ru.kizup.wotblitzhelper.di.achievements.AchievementsModule;
import ru.kizup.wotblitzhelper.di.common_info.CommonInfoComponent;
import ru.kizup.wotblitzhelper.di.common_info.CommonInfoModule;
import ru.kizup.wotblitzhelper.di.crew_skills.CrewSkillsComponent;
import ru.kizup.wotblitzhelper.di.crew_skills.CrewSkillsModule;
import ru.kizup.wotblitzhelper.di.main.MainComponent;
import ru.kizup.wotblitzhelper.di.main.MainModule;
import ru.kizup.wotblitzhelper.di.vehicles.VehiclesComponent;
import ru.kizup.wotblitzhelper.di.vehicles.VehiclesModule;
import ru.kizup.wotblitzhelper.di.view_vehicle.ViewVehicleComponent;
import ru.kizup.wotblitzhelper.di.view_vehicle.ViewVehicleModule;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Component(modules = {
        AppModule.class,
        UtilsModule.class,
        NetworkModule.class
})
@Singleton
public interface AppComponent {

    MainComponent with(MainModule module);

    CommonInfoComponent with(CommonInfoModule module);

    AchievementsComponent with(AchievementsModule module);

    CrewSkillsComponent with(CrewSkillsModule module);

    VehiclesComponent with(VehiclesModule module);

    ViewVehicleComponent with(ViewVehicleModule module);

}
