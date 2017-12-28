package ru.kizup.wotblitzhelper.presentation.presenter.crew_skills;

import android.util.Log;

import java.util.List;
import java.util.Map;

import ru.kizup.wotblitzhelper.business.crew_skills.ICrewSkillsInteractor;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillSectionUIModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;
import ru.kizup.wotblitzhelper.presentation.view.crew_skills.ICrewSkillsView;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillsPresenter implements ICrewSkillsPresenter {

    private ICrewSkillsView mView;
    private ICrewSkillsInteractor mCrewSkillsInteractor;
    private RxSchedulersAbs mRxSchedulersAbs;
    private ResponseValidator mValidator;

    public CrewSkillsPresenter(ICrewSkillsInteractor crewSkillsInteractor,
                               RxSchedulersAbs rxSchedulersAbs,
                               ResponseValidator validator) {
        mCrewSkillsInteractor = crewSkillsInteractor;
        mRxSchedulersAbs = rxSchedulersAbs;
        mValidator = validator;
    }

    @Override
    public void bindView(ICrewSkillsView view) {
        mView = view;
    }

    @Override
    public void unbindView() {
        mView = null;
    }

    @Override
    public void loadCrewSkills() {
        mView.showLoading();
        mCrewSkillsInteractor.getCrewSkills()
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessLoadedCrewSkills, this::handleFailureLoadingCrewSkills);
    }

    @Override
    public void clickOnCrewSkill(CrewSkillUIModel model) {
        mView.showDetailCrewSkillInfo(model);
    }

    private void handleSuccessLoadedCrewSkills(Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> crewSkills) {
        mView.hideLoading();
        mView.showLoadedCrewSkills(crewSkills);
    }

    private void handleFailureLoadingCrewSkills(Throwable throwable) {
        throwable.printStackTrace();
        mView.hideLoading();
        if (throwable instanceof FailureResponseException) {
            mView.showErrorLoadCrewSkills(mValidator.getErrorDescription(((FailureResponseException) throwable).getError()));
        } else {
            mView.showErrorLoadCrewSkills(mValidator.getDefaultErrorDescription());
        }
    }

}
