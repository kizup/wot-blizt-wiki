package ru.kizup.wotblitzhelper.presentation;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ru.kizup.wotblitzhelper.business.crew_skills.ICrewSkillsInteractor;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;
import ru.kizup.wotblitzhelper.presentation.presenter.crew_skills.CrewSkillCache;
import ru.kizup.wotblitzhelper.presentation.presenter.crew_skills.CrewSkillsPresenter;
import ru.kizup.wotblitzhelper.presentation.presenter.crew_skills.ICrewSkillsPresenter;
import ru.kizup.wotblitzhelper.presentation.view.crew_skills.ICrewSkillsView;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersTest;

import static org.mockito.Mockito.mock;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillsPresenterTest {

    private ICrewSkillsInteractor mCrewSkillsInteractor;
    private ICrewSkillsPresenter mCrewSkillsPresenter;
    private ICrewSkillsView mCrewSkillsView;
    private List<CrewSkillUIModel> mModels;

    @Before
    public void beforeEachTest() {
        RxSchedulersAbs rxSchedulersAbs = new RxSchedulersTest();
        mCrewSkillsInteractor = mock(ICrewSkillsInteractor.class);
        CrewSkillCache crewSkillCache = mock(CrewSkillCache.class);
        mCrewSkillsPresenter = new CrewSkillsPresenter(mCrewSkillsInteractor, rxSchedulersAbs, new ResponseValidator(mock(Context.class)), crewSkillCache);
        mCrewSkillsView = mock(ICrewSkillsView.class);
        mModels = getUIModels();
    }

    @Test
    public void loadCrewSkills_success() {
//        when(mCrewSkillsInteractor.getCrewSkills())
//                .thenReturn(Single.fromCallable(() -> mModels));
//
//        mCrewSkillsPresenter.bindView(mCrewSkillsView);
//        mCrewSkillsPresenter.loadCrewSkills();
//
//        InOrder inOrder = Mockito.inOrder(mCrewSkillsView);
//        inOrder.verify(mCrewSkillsView).showLoading();
//        inOrder.verify(mCrewSkillsView).hideLoading();
//        inOrder.verify(mCrewSkillsView).showLoadedCrewSkills(mModels);
//
//        inOrder.verify(mCrewSkillsView, never()).showErrorLoadCrewSkills("");
    }

    private List<CrewSkillUIModel> getUIModels() {
        List<CrewSkillUIModel> models = new ArrayList<>();
        CrewSkillUIModel model = new CrewSkillUIModel("1", "Name", "imageurl", "tip", "effect");
        models.add(model);
        return models;
    }

}
