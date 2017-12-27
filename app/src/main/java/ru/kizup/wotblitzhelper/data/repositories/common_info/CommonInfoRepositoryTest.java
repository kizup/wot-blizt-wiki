package ru.kizup.wotblitzhelper.data.repositories.common_info;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.base.Constants;
import ru.kizup.wotblitzhelper.data.network.common_info.response.CommonInfoModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoRepositoryTest implements ICommonInfoRepository {

    @Override
    public Single<BaseResponse<CommonInfoModel>> getCommonInfo() {
        return Observable.timer(2, TimeUnit.SECONDS)
                .flatMapSingle(aLong -> Single.fromCallable(() -> {
                    HashMap languages = new HashMap();
                    languages.put("ru", "Russian");
                    languages.put("en", "English");

                    HashMap vehicleTypes = new HashMap();
                    vehicleTypes.put("heavyTank", "Heavy Tank");
                    vehicleTypes.put("mediumTank", "Medium Tank");

                    HashMap<String, String> vehicleNations = new HashMap<>();
                    vehicleNations.put("ussr", "USSR");
                    vehicleNations.put("usa", "USA");
                    return new BaseResponse<>(Constants.OK_STATUS, new CommonInfoModel(1513779369L, languages, vehicleTypes, vehicleNations, "4.5.0.1"));
                }))
                .singleOrError();
    }

}
