package ru.kizup.wotblitzhelper.models.common_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoDataModel extends RealmObject {

    @SerializedName("achievement_sections")
    @Expose
    @Ignore
    private HashMap<String, AchievementSectionDataModel> achievementsSections;
    @SerializedName("tanks_updated_at")
    @Expose
    private Long tanksUpdatedAt;
    @SerializedName("languages")
    @Expose
    @Ignore
    private HashMap<String, String> languages;
    @SerializedName("vehicle_types")
    @Expose
    @Ignore
    private HashMap<String, String> vehicleTypes;
    @SerializedName("vehicle_nations")
    @Expose
    @Ignore
    private HashMap<String, String> vehicleNations;
    @SerializedName("game_version")
    @Expose
    private String gameVersion;

    private RealmList<Language> languagesList = new RealmList<>();
    private RealmList<VehicleTypeDao> vehicleTypesList = new RealmList<>();
    private RealmList<VehicleNationDao> vehicleNationsList = new RealmList<>();
    private RealmList<AchievementSectionDataModel> achievementsSectionsList = new RealmList<>();

    public CommonInfoDataModel(Long tanksUpdatedAt,
                               HashMap<String, String> languages,
                               HashMap<String, String> vehicleTypes,
                               HashMap<String, String> vehicleNations,
                               String gameVersion) {
        this.tanksUpdatedAt = tanksUpdatedAt;
        this.languages = languages;
        this.vehicleTypes = vehicleTypes;
        this.vehicleNations = vehicleNations;
        this.gameVersion = gameVersion;
    }

    public CommonInfoDataModel() {
    }

    public HashMap<String, AchievementSectionDataModel> getAchievementSections() {
        return achievementsSections;
    }

    public Long getTanksUpdatedAt() {
        return tanksUpdatedAt;
    }

    public HashMap<String, String> getLanguages() {
        return languages;
    }

    public HashMap<String, String> getVehicleTypes() {
        return vehicleTypes;
    }

    public HashMap<String, String> getVehicleNations() {
        return vehicleNations;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public RealmList<Language> getLanguagesList() {
        return languagesList;
    }

    public void setLanguagesList(RealmList<Language> languagesList) {
        this.languagesList = languagesList;
    }

    public RealmList<VehicleTypeDao> getVehicleTypesList() {
        return vehicleTypesList;
    }

    public void setVehicleTypesList(RealmList<VehicleTypeDao> vehicleTypesList) {
        this.vehicleTypesList = vehicleTypesList;
    }

    public RealmList<VehicleNationDao> getVehicleNationsList() {
        return vehicleNationsList;
    }

    public void setVehicleNationsList(RealmList<VehicleNationDao> vehicleNationsList) {
        this.vehicleNationsList = vehicleNationsList;
    }

    public RealmList<AchievementSectionDataModel> getAchievementsSectionsList() {
        return achievementsSectionsList;
    }

    public void setAchievementsSectionsList(RealmList<AchievementSectionDataModel> achievementsSectionsList) {
        this.achievementsSectionsList = achievementsSectionsList;
    }
}
