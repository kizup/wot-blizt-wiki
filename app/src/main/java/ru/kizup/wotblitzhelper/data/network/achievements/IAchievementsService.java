package ru.kizup.wotblitzhelper.data.network.achievements;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.network.achievements.response.AchievementsModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IAchievementsService {

    @GET("achievements/")
    Observable<BaseResponse<HashMap<String, AchievementsModel>>> getAchievements(@Query("fields") String fields);

}
