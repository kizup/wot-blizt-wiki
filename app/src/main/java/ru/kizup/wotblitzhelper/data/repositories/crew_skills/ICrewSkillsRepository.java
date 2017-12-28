package ru.kizup.wotblitzhelper.data.repositories.crew_skills;

import java.util.HashMap;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillDataModel;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICrewSkillsRepository {

    Single<HashMap<String, CrewSkillDataModel>> getAllCrewSkills();

    Single<HashMap<String, String>> getAllVehicleTypes();

}
