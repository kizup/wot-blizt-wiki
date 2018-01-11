package ru.kizup.wotblitzhelper.data.db;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.realm.RealmObject;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillDataModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.EngineDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.GunDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.SuspensionDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.TurretDataModel;

/**
 * Created by: dpuzikov on 29.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IDatabaseHelper {

    Single<HashMap<String, AchievementsModel>> getAchievements();

    Single<Boolean> saveModel(RealmObject model);

    Single<HashMap<String, String>> getVehicleTypes();

    Single<HashMap<String, String>> getVehicleNations();

    Single<HashMap<String, CrewSkillDataModel>> getCrewSkills();

    Single<List<ShortVehicleInfoDataModel>> getAllVehicles();

    Single<List<ShortVehicleInfoDataModel>> getAllVehiclesByTier();

    Single<DetailVehicleDataModel> getDetailVehicleInfo(int id);

    Single<List<ShortVehicleInfoDataModel>> getVehiclesByIds(Integer[] ids);

    Single<List<TurretDataModel>> getTurretsForVehicle(int vehicleId);

    Single<List<GunDataModel>> getGunsForVehicle(int vehicleId);

    Single<List<EngineDataModel>> getEnginesForVehicle(int vehicleId);

    Single<List<SuspensionDataModel>> getSuspensionsForVehicle(int vehicleId);

    Single<CommonInfoDataModel> getCommonWikiInfo();

}
