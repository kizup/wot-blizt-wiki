package ru.kizup.wotblitzhelper.data.repositories.crew_skills;

import java.util.HashMap;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoDataModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillDataModel;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillsRepository implements ICrewSkillsRepository {

    private IApiService mApiService;

    public CrewSkillsRepository(IApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<HashMap<String, CrewSkillDataModel>> getAllCrewSkills() {
        return mApiService.getAllCrewSkills()
                .flatMapSingle(response -> {
                    if (!response.isSuccess()) {
                        return Single.error(new FailureResponseException(response.getError()));
                    }
                    return Single.fromCallable(() -> response);
                })
                .map(BaseResponse::getData)
                .singleOrError();
    }

    @Override
    public Single<HashMap<String, String>> getAllVehicleTypes() {
        return mApiService.getCommonInfo("vehicle_types")
                .flatMapSingle(response -> {
                    if (!response.isSuccess()) {
                        return Single.error(new FailureResponseException(response.getError()));
                    }
                    return Single.fromCallable(() -> response);
                })
                .map(BaseResponse::getData)
                .map(CommonInfoDataModel::getVehicleTypes)
                .singleOrError();
    }
}
