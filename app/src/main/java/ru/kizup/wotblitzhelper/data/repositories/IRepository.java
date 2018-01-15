package ru.kizup.wotblitzhelper.data.repositories;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.models.common_info.AchievementSectionDataModel;

/**
 * Created by: dpuzikov on 29.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IRepository {

    Single<HashMap<String, String>> getAllVehicleTypes();

    Single<HashMap<String, String>> getAllVehicleNations();

}
