package ru.kizup.wotblitzhelper.data.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;
import ru.kizup.wotblitzhelper.models.common_info.AchievementDao;
import ru.kizup.wotblitzhelper.models.common_info.VehicleNationDao;
import ru.kizup.wotblitzhelper.models.common_info.VehicleTypeDao;
import ru.kizup.wotblitzhelper.models.crew_skills.CrewSkillDataModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.EngineDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.GunDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.SuspensionDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.TurretDataModel;

/**
 * Created by: dpuzikov on 29.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class DatabaseHelper implements IDatabaseHelper {

    private static final String TANK_ID = "tankId";
    private static final String MODULE_ID = "id";

    public DatabaseHelper(Context context) {
        Realm.init(context);
    }

    @Override
    public Single<HashMap<String, AchievementsModel>> getAchievements() {
        return Single.fromCallable(() -> {
            List<AchievementDao> daos = getRealm().copyFromRealm(getRealm()
                    .where(AchievementDao.class)
                    .findAll());
            HashMap<String, AchievementsModel> models = new HashMap<>();
            for (AchievementDao dao : daos) {
                models.put(dao.getCode(), mapDaoToModel(dao));
            }
            close();
            return models;
        });
    }

    @Override
    public Single<Boolean> saveModel(RealmObject model) {
        return Single.fromCallable(() -> insert(model));
    }

    @Override
    public Single<HashMap<String, String>> getVehicleTypes() {
        return Single.fromCallable(() -> {
            List<VehicleTypeDao> daos = getRealm().copyFromRealm(getRealm()
                    .where(VehicleTypeDao.class)
                    .findAll());
            HashMap<String, String> models = new HashMap<>();
            for (VehicleTypeDao dao : daos) {
                models.put(dao.getCode(), dao.getName());
            }
            close();
            return models;
        });
    }

    @Override
    public Single<HashMap<String, String>> getVehicleNations() {
        return Single.fromCallable(() -> {
            List<VehicleNationDao> daos = getRealm().copyFromRealm(getRealm()
                    .where(VehicleNationDao.class)
                    .findAll());
            HashMap<String, String> models = new HashMap<>();
            for (VehicleNationDao dao : daos) {
                models.put(dao.getCode(), dao.getName());
            }
            close();
            return models;
        });
    }

    @Override
    public Single<HashMap<String, CrewSkillDataModel>> getCrewSkills() {
        return Single.fromCallable(() -> {
            List<CrewSkillDataModel> daos = getRealm().copyFromRealm(getRealm()
                    .where(CrewSkillDataModel.class)
                    .findAll());
            HashMap<String, CrewSkillDataModel> models = new HashMap<>();
            for (CrewSkillDataModel dao : daos) {
                models.put(dao.getSkillId(), dao);
            }
            close();
            return models;
        });
    }

    @Override
    public Single<List<ShortVehicleInfoDataModel>> getAllVehicles() {
        return Single.fromCallable(() -> {
            List<ShortVehicleInfoDataModel> models = getRealm().copyFromRealm(getRealm()
                    .where(ShortVehicleInfoDataModel.class)
                    .findAll());
            close();
            return models;
        });
    }

    @Override
    public Single<List<ShortVehicleInfoDataModel>> getAllVehiclesByTier() {
        return Single.fromCallable(() -> {
            List<ShortVehicleInfoDataModel> models = getRealm().copyFromRealm(getRealm()
                    .where(ShortVehicleInfoDataModel.class)
                    .sort("tier")
                    .findAll());
            close();
            return models;
        });
    }

    @Override
    public Single<DetailVehicleDataModel> getDetailVehicleInfo(int id) {
        return Single.fromCallable(() -> {
            Realm realm = getRealm();
            RealmQuery<DetailVehicleDataModel> query = realm.where(DetailVehicleDataModel.class).equalTo(TANK_ID, id);
            if (query.count() != 1) {
                return DetailVehicleDataModel.empty();
            } else {
                DetailVehicleDataModel model = query.findFirst();
                if (model == null) {
                    close();
                    return DetailVehicleDataModel.empty();
                }

                DetailVehicleDataModel searchedVehicle = query.findFirst();
                if (searchedVehicle == null) {
                    close();
                    return DetailVehicleDataModel.empty();
                }

                model = realm.copyFromRealm(searchedVehicle);
                close();
                return model;
            }
        });
    }

    @Override
    public Single<List<ShortVehicleInfoDataModel>> getVehiclesByIds(Integer[] ids) {
        return Single.fromCallable(() -> {
            Realm realm = getRealm();
            List<ShortVehicleInfoDataModel> models = realm.copyFromRealm(realm.where(ShortVehicleInfoDataModel.class)
                    .in(TANK_ID, ids)
                    .findAll());
            close();
            return models;
        });
    }

    @Override
    public Single<List<TurretDataModel>> getTurretsForVehicle(int vehicleId) {
        return Single.fromCallable(() -> {
            Realm realm = getRealm();
            RealmQuery<DetailVehicleDataModel> query = realm.where(DetailVehicleDataModel.class)
                    .equalTo(TANK_ID, vehicleId);

            List<TurretDataModel> turrets = new ArrayList<>();

            if (query != null) {
                DetailVehicleDataModel searchedVehicle = query.findFirst();
                if (searchedVehicle == null) {
                    close();
                    return turrets;
                }
                DetailVehicleDataModel vehicle = realm.copyFromRealm(searchedVehicle);
                Integer[] turretIds = vehicle.getTurretsArray();

                RealmResults<TurretDataModel> searchResults = realm.where(TurretDataModel.class)
                        .in(MODULE_ID, turretIds)
                        .findAll();
                if (searchResults.size() == turretIds.length) {
                    turrets.addAll(realm.copyFromRealm(searchResults));
                } else {
                    close();
                    return turrets;
                }
            }
            close();
            return turrets;
        });
    }

    @Override
    public Single<List<GunDataModel>> getGunsForVehicle(int vehicleId) {
        return Single.fromCallable(() -> {
            Realm realm = getRealm();
            RealmQuery<DetailVehicleDataModel> query = realm.where(DetailVehicleDataModel.class)
                    .equalTo(TANK_ID, vehicleId);

            List<GunDataModel> guns = new ArrayList<>();

            if (query != null) {
                DetailVehicleDataModel searchedVehicle = query.findFirst();
                if (searchedVehicle == null) {
                    close();
                    return guns;
                }
                DetailVehicleDataModel vehicle = realm.copyFromRealm(searchedVehicle);
                Integer[] gunIds = vehicle.getGunsArray();

                RealmResults<GunDataModel> searchResults = realm.where(GunDataModel.class)
                        .in(MODULE_ID, gunIds)
                        .findAll();
                if (searchResults.size() == gunIds.length) {
                    guns.addAll(realm.copyFromRealm(searchResults));
                } else {
                    close();
                    return guns;
                }
            }
            close();
            return guns;
        });
    }

    @Override
    public Single<List<EngineDataModel>> getEnginesForVehicle(int vehicleId) {
        return Single.fromCallable(() -> {
            Realm realm = getRealm();
            RealmQuery<DetailVehicleDataModel> query = realm.where(DetailVehicleDataModel.class)
                    .equalTo(TANK_ID, vehicleId);

            List<EngineDataModel> engines = new ArrayList<>();

            if (query != null) {
                DetailVehicleDataModel searchedVehicle = query.findFirst();
                if (searchedVehicle == null) {
                    close();
                    return engines;
                }
                DetailVehicleDataModel vehicle = realm.copyFromRealm(searchedVehicle);
                Integer[] engineIds = vehicle.getEnginesArray();

                RealmResults<EngineDataModel> searchResults = realm.where(EngineDataModel.class)
                        .in(MODULE_ID, engineIds)
                        .findAll();
                if (searchResults.size() == engineIds.length) {
                    engines.addAll(realm.copyFromRealm(searchResults));
                } else {
                    close();
                    return engines;
                }
            }
            close();
            return engines;
        });
    }

    @Override
    public Single<List<SuspensionDataModel>> getSuspensionsForVehicle(int vehicleId) {
        return Single.fromCallable(() -> {
            Realm realm = getRealm();
            RealmQuery<DetailVehicleDataModel> query = realm.where(DetailVehicleDataModel.class)
                    .equalTo(TANK_ID, vehicleId);

            List<SuspensionDataModel> suspensions = new ArrayList<>();

            if (query != null) {
                DetailVehicleDataModel searchedVehicle = query.findFirst();
                if (searchedVehicle == null) {
                    close();
                    return suspensions;
                }
                DetailVehicleDataModel vehicle = realm.copyFromRealm(searchedVehicle);
                Integer[] suspensionIds = vehicle.getSuspensionsArray();

                RealmResults<SuspensionDataModel> searchResults = realm.where(SuspensionDataModel.class)
                        .in(MODULE_ID, suspensionIds)
                        .findAll();
                if (searchResults.size() == suspensionIds.length) {
                    suspensions.addAll(realm.copyFromRealm(searchResults));
                } else {
                    close();
                    return suspensions;
                }
            }
            close();
            return suspensions;
        });
    }

    private AchievementsModel mapDaoToModel(AchievementDao dao) {
        return new AchievementsModel(dao.getAchievementId(),
                dao.getOrder(),
                dao.getDescription(),
                dao.getImage(),
                dao.getImageBig(),
                dao.getName()
        );
    }

    private synchronized void close() {
        getRealm().close();
    }

    private boolean insert(RealmObject object) {
        Realm realm = getRealm();
        try {
            realm.beginTransaction();
            realm.insertOrUpdate(object);
            realm.commitTransaction();
            close();
            return true;
        } catch (RealmException e) {
            e.printStackTrace();
            realm.cancelTransaction();
            close();
            return false;
        }
    }

    private synchronized Realm getRealm() {
        return Realm.getDefaultInstance();
    }

}
