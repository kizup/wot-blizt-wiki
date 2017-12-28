package ru.kizup.wotblitzhelper.business.crew_skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.kizup.wotblitzhelper.data.repositories.crew_skills.ICrewSkillsRepository;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillDataModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillSectionUIModel;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillUIModel;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillsInteractor
        implements ICrewSkillsInteractor {

    private ICrewSkillsRepository mCrewSkillsRepository;

    public CrewSkillsInteractor(ICrewSkillsRepository crewSkillsRepository) {
        mCrewSkillsRepository = crewSkillsRepository;
    }

    @Override
    public Single<Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>>> getCrewSkills() {
        return Single.zip(getCrewSkillsFromData(), mCrewSkillsRepository.getAllVehicleTypes(), this::convert);
    }

    private Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> convert(List<CrewSkillDataModel> skillsList, Map<String, String> vehicleTypes) {
        Map<CrewSkillSectionUIModel, List<CrewSkillUIModel>> map = new HashMap<>();

        List<CrewSkillSectionUIModel> sections = new ArrayList<>();
        for (String key : vehicleTypes.keySet()) {
            sections.add(new CrewSkillSectionUIModel(key, vehicleTypes.get(key)));
        }

        for (CrewSkillSectionUIModel section : sections) {
            if (!map.containsKey(section)) map.put(section, new ArrayList<>());

            for (CrewSkillDataModel skillDataModel : skillsList) {
                if (skillDataModel.getVehicleType().equals(section.getId())) {
                    map.get(section).add(mapModel(skillDataModel));
                }
            }
        }

        return map;
    }

    private Single<List<CrewSkillDataModel>> getCrewSkillsFromData() {
        return mCrewSkillsRepository.getAllCrewSkills()
                .flatMapObservable(map -> Observable.fromIterable(map.values()))
                .toSortedList((o1, o2) -> o1.getName().compareTo(o2.getName()));
    }

    private CrewSkillUIModel mapModel(CrewSkillDataModel model) {
        if (model.getFeatures() == null) {
            return new CrewSkillUIModel(model.getSkillId(),
                    model.getName(),
                    model.getImages().getLarge(),
                    model.getTip(),
                    model.getEffect());
        }

        return new CrewSkillUIModel(model.getSkillId(),
                model.getName(),
                model.getImages().getLarge(),
                model.getTip(),
                model.getEffect(),
                model.getFeatures());
    }

}
