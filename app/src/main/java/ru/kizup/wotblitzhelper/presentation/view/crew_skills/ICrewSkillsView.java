package ru.kizup.wotblitzhelper.presentation.view.crew_skills;

import java.util.List;
import java.util.Map;

import ru.kizup.wotblitzhelper.base.BaseView;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillSectionUIModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICrewSkillsView extends BaseView {

    void showLoading();

    void hideLoading();

    void showErrorLoadCrewSkills(String error);

    void showLoadedCrewSkills(Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> models);

    void showDetailCrewSkillInfo(CrewSkillUIModel model);

    void showEmptySidePanel();

    void hideSidePanel();

    void showSidePanel(CrewSkillUIModel model);

}
