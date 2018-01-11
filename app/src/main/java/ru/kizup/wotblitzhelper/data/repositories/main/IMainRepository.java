package ru.kizup.wotblitzhelper.data.repositories.main;

import java.util.HashMap;

import io.reactivex.Single;
import io.realm.RealmObject;
import retrofit2.Response;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillDataModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModulesDataModel;

/**
 * Created by: dpuzikov on 11.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IMainRepository {

    Single<Boolean> saveModel(RealmObject model);

    Single<Response<BaseResponse<HashMap<String, ShortVehicleInfoDataModel>>>> getAllVehiclesFromServer();

    Single<Response<BaseResponse<HashMap<String, DetailVehicleDataModel>>>> getAllVehiclesDetailInfoFromServer();

    Single<Response<BaseResponse<ModulesDataModel>>> getAllModulesForVehicleFromServer();

    Single<Response<BaseResponse<CommonInfoDataModel>>> getCommonWikiInfoFromServer();

    Single<Response<BaseResponse<HashMap<String, AchievementsModel>>>> getAllAchievementsFromServer();

    Single<Response<BaseResponse<HashMap<String, CrewSkillDataModel>>>> getAllCrewSkillsFromServer();

    Single<CommonInfoDataModel> getCommonInfoFromDatabase();

    Single<Response<BaseResponse<CommonInfoDataModel>>> getGameVersionFromServer();

}
