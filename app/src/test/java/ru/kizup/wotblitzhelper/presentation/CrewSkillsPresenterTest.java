package ru.kizup.wotblitzhelper.presentation;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.business.crew_skills.ICrewSkillsInteractor;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;
import ru.kizup.wotblitzhelper.presentation.presenter.crew_skills.CrewSkillsPresenter;
import ru.kizup.wotblitzhelper.presentation.presenter.crew_skills.ICrewSkillsPresenter;
import ru.kizup.wotblitzhelper.presentation.view.crew_skills.ICrewSkillsView;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

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
        mCrewSkillsPresenter = new CrewSkillsPresenter(mCrewSkillsInteractor, rxSchedulersAbs, new ResponseValidator(mock(Context.class)));
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
