package ru.kizup.wotblitzhelper.presentation.presenter.crew_skills;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillSectionUIModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;

/**
 * Created by: dpuzikov on 29.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillCache {

    private Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> mCrewSkills;

    public CrewSkillCache() {
    }

    public void saveCrewSkills(Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> crewSkills) {
        mCrewSkills = new HashMap<>();
        mCrewSkills.putAll(crewSkills);
    }

    public boolean isCacheSaved() {
        return mCrewSkills != null
                && !mCrewSkills.isEmpty();
    }

    public Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> getCrewSkills() {
        return mCrewSkills;
    }
}
