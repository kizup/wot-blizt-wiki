package ru.kizup.wotblitzhelper.data.network;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillDataModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModulesDataModel;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IApiService {

    @GET("achievements/")
    Single<BaseResponse<HashMap<String, AchievementsModel>>> getAchievements(@Query("fields") String fields);

    @GET("info/")
    Observable<BaseResponse<CommonInfoDataModel>> getCommonInfo();

    @GET("info/")
    Observable<BaseResponse<CommonInfoDataModel>> getCommonInfo(@Query("fields") String fields);

    @GET("crewskills/")
    Observable<BaseResponse<HashMap<String, CrewSkillDataModel>>> getAllCrewSkills();

    @GET("vehicles/")
    Observable<Response<BaseResponse<HashMap<String, ShortVehicleInfoDataModel>>>> getShortAllVehicleInfo(@Query("fields") String fields);

    @GET("vehicles/")
    Observable<BaseResponse<HashMap<String, DetailVehicleDataModel>>> getDetailVehicleInfo(@Query("tank_id") int id);

    @GET("modules/")
    Observable<Response<BaseResponse<ModulesDataModel>>> getModulesList(@Query("module_id") String moduleIds, @Query("type") String moduleType);

}
