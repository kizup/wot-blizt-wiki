package ru.kizup.wotblitzhelper.data.repositories.common_info;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoRepositoryTest implements ICommonInfoRepository {
    @Override
    public Single<CommonInfoDataModel> getCommonInfo() {
        return null;
    }

//    @Override
//    public Single<BaseResponse<CommonInfoDataModel>> getCommonInfo() {
//        return Observable.timer(2, TimeUnit.SECONDS)
//                .flatMapSingle(aLong -> Single.fromCallable(() -> {
//                    HashMap languages = new HashMap();
//                    languages.put("ru", "Russian");
//                    languages.put("en", "English");
//
//                    HashMap vehicleTypes = new HashMap();
//                    vehicleTypes.put("heavyTank", "Heavy Tank");
//                    vehicleTypes.put("mediumTank", "Medium Tank");
//
//                    HashMap<String, String> vehicleNations = new HashMap<>();
//                    vehicleNations.put("ussr", "USSR");
//                    vehicleNations.put("usa", "USA");
//                    return new BaseResponse<>(Constants.OK_STATUS, new CommonInfoDataModel(1513779369L, languages, vehicleTypes, vehicleNations, "4.5.0.1"));
//                }))
//                .singleOrError();
//    }

}
