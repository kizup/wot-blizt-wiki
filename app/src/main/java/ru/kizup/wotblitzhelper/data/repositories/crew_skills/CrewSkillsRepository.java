package ru.kizup.wotblitzhelper.data.repositories.crew_skills;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.Response;
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
        return getDatabaseHelper().getCrewSkills();
    }

}
