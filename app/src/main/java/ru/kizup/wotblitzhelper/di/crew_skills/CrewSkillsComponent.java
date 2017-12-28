package ru.kizup.wotblitzhelper.di.crew_skills;

import dagger.Subcomponent;
import ru.kizup.wotblitzhelper.presentation.view.crew_skills.CrewSkillsFragment;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

@Subcomponent(modules = {
        CrewSkillsModule.class
})
@CrewSkillsScope
public interface CrewSkillsComponent {

    void inject(CrewSkillsFragment fragment);

}
