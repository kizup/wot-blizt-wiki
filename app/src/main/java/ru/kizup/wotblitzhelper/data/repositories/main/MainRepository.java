package ru.kizup.wotblitzhelper.data.repositories.main;

import java.util.HashMap;

import io.reactivex.Single;
import io.realm.RealmObject;
import retrofit2.Response;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.Repository;
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

public class MainRepository extends Repository
        implements IMainRepository {

    public MainRepository(IApiService apiService, IDatabaseHelper helper) {
        super(apiService, helper);
    }

    @Override
    public Single<Boolean> saveModel(RealmObject model) {
        return getDatabaseHelper().saveModel(model);
    }

    @Override
    public Single<Response<BaseResponse<HashMap<String, ShortVehicleInfoDataModel>>>> getAllVehiclesFromServer() {
        return getApiService().getShortAllVehicleInfo("tank_id,description,name,nation,tier,type,cost,images")
                .singleOrError();
    }

    @Override
    public Single<Response<BaseResponse<HashMap<String, DetailVehicleDataModel>>>> getAllVehiclesDetailInfoFromServer() {
        return getApiService().getAllDetailVehicleInfo()
                .singleOrError();
    }

    @Override
    public Single<Response<BaseResponse<ModulesDataModel>>> getAllModulesForVehicleFromServer() {
        return getApiService().getModulesList()
                .singleOrError();
    }

    @Override
    public Single<Response<BaseResponse<CommonInfoDataModel>>> getCommonWikiInfoFromServer() {
        return getApiService().getCommonInfo();
    }

    @Override
    public Single<Response<BaseResponse<HashMap<String, AchievementsModel>>>> getAllAchievementsFromServer() {
        return getApiService().getAllAchievements();
    }

    @Override
    public Single<Response<BaseResponse<HashMap<String, CrewSkillDataModel>>>> getAllCrewSkillsFromServer() {
        return getApiService().getAllCrewSkills();
    }

    @Override
    public Single<CommonInfoDataModel> getCommonInfoFromDatabase() {
        return getDatabaseHelper().getCommonWikiInfo();
    }

    @Override
    public Single<Response<BaseResponse<CommonInfoDataModel>>> getGameVersionFromServer() {
        return getApiService().getCommonInfo("game_version");
    }
}
