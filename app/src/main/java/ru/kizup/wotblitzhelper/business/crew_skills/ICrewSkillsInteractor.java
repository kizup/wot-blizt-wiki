package ru.kizup.wotblitzhelper.business.crew_skills;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillSectionUIModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICrewSkillsInteractor {

    Single<Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>>> getCrewSkills();

}
