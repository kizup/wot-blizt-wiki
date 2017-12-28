package ru.kizup.wotblitzhelper.data.network;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillDataModel;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IApiService {

    @GET("achievements/")
    Observable<BaseResponse<HashMap<String, AchievementsModel>>> getAchievements(@Query("fields") String fields);

    @GET("info/")
    Observable<BaseResponse<CommonInfoDataModel>> getCommonInfo();

    @GET("info/")
    Observable<BaseResponse<CommonInfoDataModel>> getCommonInfo(@Query("fields") String fields);

    @GET("crewskills/")
    Observable<BaseResponse<HashMap<String, CrewSkillDataModel>>> getAllCrewSkills();

}
