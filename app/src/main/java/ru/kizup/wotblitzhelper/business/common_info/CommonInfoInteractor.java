package ru.kizup.wotblitzhelper.business.common_info;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.common_info.response.AchievementInfoModel;
import ru.kizup.wotblitzhelper.data.network.common_info.response.CommonInfoModel;
import ru.kizup.wotblitzhelper.data.repositories.common_info.ICommonInfoRepository;
import ru.kizup.wotblitzhelper.presentation.common_info.models.AchievementInfo;
import ru.kizup.wotblitzhelper.presentation.common_info.models.CommonInfo;
import ru.kizup.wotblitzhelper.presentation.common_info.models.VehicleType;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoInteractor implements ICommonInfoInteractor {

    private ICommonInfoRepository mCommonInfoRepository;
    private ResponseValidator mResponseValidator;

    public CommonInfoInteractor(ICommonInfoRepository commonInfoRepository,
                                ResponseValidator validator) {
        mCommonInfoRepository = commonInfoRepository;
        mResponseValidator = validator;
    }

    @Override
    public Single<CommonInfo> getCommonInfo() {
        return mCommonInfoRepository.getCommonInfo()
                .map(model -> {
                    if (model == null) {
                        throw new CommonInfoLoadException("CommonInfo is null");
                    }
                    return model;
                })
                .flatMap(response -> {
                    if (!response.isSuccess()) {
                        return Single.error(new FailureResponseException(mResponseValidator.getErrorDescription(response.getError())));
                    }
                    return Single.fromCallable(() -> response);
                })
                .map(BaseResponse::getData)
                .map(this::convert);
    }

    private CommonInfo convert(CommonInfoModel model) {
        long timestamp = TimeUnit.SECONDS.toMillis(model.getTanksUpdatedAt());
        Date updatedAtDate = new Date(timestamp);
        return new CommonInfo(
                model.getGameVersion(),
                updatedAtDate,
                new ArrayList<>(model.getLanguages().values()),
                getVehicleTypes(model.getVehicleTypes()),
                getAchievments(model.getAchievments()));
    }

    private List<AchievementInfo> getAchievments(HashMap<String, AchievementInfoModel> achievmentsMap) {
        List<AchievementInfo> achievementInfos = new ArrayList<>();
        for (String key : achievmentsMap.keySet()) {
            AchievementInfoModel model = achievmentsMap.get(key);
            achievementInfos.add(new AchievementInfo(model.getName(), key, model.getOrder()));
        }

        Collections.sort(achievementInfos, compareAchievements());
        return achievementInfos;
    }

    private List<VehicleType> getVehicleTypes(HashMap<String, String> types) {
        List<VehicleType> vehicleTypes = new ArrayList<>(types.size());
        for (String key : types.keySet()) {
            vehicleTypes.add(new VehicleType(key, types.get(key)));
        }
        return vehicleTypes;
    }

    private Comparator<AchievementInfo> compareAchievements () {
        return (o1, o2) -> o1.getOrder() - o2.getOrder();
    }

}
