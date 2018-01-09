package ru.kizup.wotblitzhelper.data.repositories.crew_skills;

import java.util.HashMap;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.base.BaseResponse;
import ru.kizup.wotblitzhelper.data.db.IDatabaseHelper;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.IApiService;
import ru.kizup.wotblitzhelper.data.repositories.Repository;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillDataModel;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillsRepository extends Repository
        implements ICrewSkillsRepository {

    public CrewSkillsRepository(IApiService apiService, IDatabaseHelper helper) {
        super(apiService, helper);
    }

    @Override
    public Single<HashMap<String, CrewSkillDataModel>> getAllCrewSkills() {
        return getDatabaseHelper().getCrewSkills()
                .flatMap(map -> {
                    if (map.isEmpty()) return getAllCrewSkillsFromServer();
                    return Single.just(map);
                });
    }

    private Single<HashMap<String, CrewSkillDataModel>> getAllCrewSkillsFromServer() {
        return getApiService().getAllCrewSkills()
                .flatMapSingle(response -> {
                    if (!response.isSuccess()) {
                        return Single.error(new FailureResponseException(response.getError()));
                    }
                    return Single.fromCallable(() -> response);
                })
                .map(BaseResponse::getData)
                .doOnNext(this::saveCrewSkills)
                .singleOrError();
    }

    private void saveCrewSkills(HashMap<String, CrewSkillDataModel> crewSkills) {
        for (String key : crewSkills.keySet()) {
            getDatabaseHelper().saveModel(crewSkills.get(key)).subscribe();
        }
    }

}
