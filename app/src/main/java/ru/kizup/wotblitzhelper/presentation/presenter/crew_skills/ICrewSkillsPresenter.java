package ru.kizup.wotblitzhelper.presentation.presenter.crew_skills;

import ru.kizup.wotblitzhelper.base.BasePresenter;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;
import ru.kizup.wotblitzhelper.presentation.view.crew_skills.ICrewSkillsView;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface ICrewSkillsPresenter extends BasePresenter<ICrewSkillsView> {

    void loadCrewSkills();

    void clickOnCrewSkill(CrewSkillUIModel model);

}
