package ru.kizup.wotblitzhelper.presentation.presenter.crew_skills;

import android.content.res.Configuration;

import java.util.List;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
    private CrewSkillCache mCrewSkillCache;
    private CompositeDisposable mCompositeDisposable;
    private int mScreenOrientation;

    public CrewSkillsPresenter(ICrewSkillsInteractor crewSkillsInteractor,
                               RxSchedulersAbs rxSchedulersAbs,
                               ResponseValidator validator,
                               CrewSkillCache crewSkillCache) {
        mCrewSkillsInteractor = crewSkillsInteractor;
        mRxSchedulersAbs = rxSchedulersAbs;
        mValidator = validator;
        mCrewSkillCache = crewSkillCache;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void bindView(ICrewSkillsView view) {
        mView = view;
    }

    @Override
    public void unbindView() {
        mCompositeDisposable.clear();
        mView = null;
    }

    @Override
    public void loadCrewSkills() {
        if (mCrewSkillCache.isCacheSaved()) {
            setCrewSkillsToView(mCrewSkillCache.getCrewSkills());
        } else {
            loadCrewSkillsFromData();
        }
    }

    private void loadCrewSkillsFromData() {
        mView.showLoading();
        Disposable disposable = mCrewSkillsInteractor.getCrewSkills()
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessLoadedCrewSkills, this::handleFailureLoadingCrewSkills);
        mCompositeDisposable.add(disposable);
    }

    private void setCrewSkillsToView(Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> crewSkills) {
        mView.showLoadedCrewSkills(crewSkills);
    }

    @Override
    public void clickOnCrewSkill(CrewSkillUIModel model) {
        if (mScreenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            mView.showSidePanel(model);
        } else {
            mView.showDetailCrewSkillInfo(model);
        }
    }

    @Override
    public void setScreenOrientation(int orientation) {
        mScreenOrientation = orientation;
        if (mScreenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            mView.showEmptySidePanel();
        } else {
            mView.hideSidePanel();
        }
    }

    private void handleSuccessLoadedCrewSkills(Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> crewSkills) {
        mView.hideLoading();
        mCrewSkillCache.saveCrewSkills(crewSkills);
        setCrewSkillsToView(crewSkills);
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
