package ru.kizup.wotblitzhelper.business.common_info;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;
import ru.kizup.wotblitzhelper.data.repositories.common_info.ICommonInfoRepository;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoUIModel;
import ru.kizup.wotblitzhelper.models.common_info.VehicleTypeUIModel;

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
    public Single<CommonInfoUIModel> getCommonInfo() {
        return mCommonInfoRepository.getCommonInfo()
                .map(this::convert);
    }

    private CommonInfoUIModel convert(CommonInfoDataModel model) {
        long timestamp = TimeUnit.SECONDS.toMillis(model.getTanksUpdatedAt());
        Date updatedAtDate = new Date(timestamp);
        return new CommonInfoUIModel(
                model.getGameVersion(),
                updatedAtDate,
                model.getLanguages().size(),
                model.getVehicleTypes().size(),
                model.getAchievementSections().size());
    }

    private List<VehicleTypeUIModel> getVehicleTypes(HashMap<String, String> types) {
        List<VehicleTypeUIModel> vehicleTypes = new ArrayList<>(types.size());
        for (String key : types.keySet()) {
            vehicleTypes.add(new VehicleTypeUIModel(key, types.get(key)));
        }
        return vehicleTypes;
    }

}
