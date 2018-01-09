package ru.kizup.wotblitzhelper.business.crew_skills;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.repositories.crew_skills.ICrewSkillsRepository;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillDataModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillImage;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillSectionUIModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillsInteractorTest {

    private ICrewSkillsRepository mCrewSkillsRepository;
    private ICrewSkillsInteractor mCrewSkillsInteractor;

    @Before
    public void beforeEachTest() {
        mCrewSkillsRepository = mock(ICrewSkillsRepository.class);
        mCrewSkillsInteractor = new CrewSkillsInteractor(mCrewSkillsRepository);
    }

    @Test
    public void getCrewSkills_success() {
        when(mCrewSkillsRepository.getAllCrewSkills())
                .thenReturn(Single.fromCallable(() -> {
                    HashMap<String, CrewSkillDataModel> models = new HashMap<>();
                    CrewSkillImage images = new CrewSkillImage("imgurl");
                    models.put("First", new CrewSkillDataModel("1", "Feature", "Tip", "Effect", images, "medium", "Skill"));
                    return models;
                }));

        when(mCrewSkillsRepository.getAllVehicleTypes()).thenReturn(Single.fromCallable(() -> {
            HashMap<String, String> types = new HashMap<>();
            types.put("medium", "Medium Tank");
            return types;
        }));

        TestObserver<Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>>> testObserver = TestObserver.create();
        mCrewSkillsInteractor.getCrewSkills().subscribe(testObserver);

        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
        testObserver.assertNoErrors();

        Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> mapForPresenter = testObserver.values().get(0);
        assertThat(mapForPresenter.size()).isEqualTo(1);
    }

    @Test
    public void getCrewSkills_exception() {
        when(mCrewSkillsRepository.getAllCrewSkills())
                .thenReturn(Single.error(new FailureResponseException()));
        when(mCrewSkillsRepository.getAllVehicleTypes()).thenReturn(Single.fromCallable(() -> {
            HashMap<String, String> types = new HashMap<>();
            types.put("medium", "Medium Tank");
            return types;
        }));

        TestObserver<Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>>> testObserver = TestObserver.create();
        mCrewSkillsInteractor.getCrewSkills().subscribe(testObserver);

        testObserver.awaitTerminalEvent();
        testObserver.assertNotComplete();
        testObserver.assertError(FailureResponseException.class);
    }

}
