package ru.kizup.wotblitzhelper.business.main;

import io.reactivex.Single;

/**
 * Created by: dpuzikov on 10.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IMainInteractor {

    Single<Boolean> updateDatabase();

    Single<Boolean> checkDatabaseUpdated();

}
