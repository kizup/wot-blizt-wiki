package ru.kizup.wotblitzhelper.data.network.common_info.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoModel {

    @SerializedName("achievement_sections")
    @Expose
    private HashMap<String, AchievementInfoModel> achievments;
    @SerializedName("tanks_updated_at")
    @Expose
    private Long tanksUpdatedAt;
    @SerializedName("languages")
    @Expose
    private HashMap<String, String> languages;
    @SerializedName("vehicle_types")
    @Expose
    private HashMap<String, String> vehicleTypes;
    @SerializedName("vehicle_nations")
    @Expose
    private HashMap<String, String> vehicleNations;
    @SerializedName("game_version")
    @Expose
    private String gameVersion;

    public CommonInfoModel(Long tanksUpdatedAt, HashMap<String, String> languages, HashMap<String, String> vehicleTypes, HashMap<String, String> vehicleNations, String gameVersion) {
        this.tanksUpdatedAt = tanksUpdatedAt;
        this.languages = languages;
        this.vehicleTypes = vehicleTypes;
        this.vehicleNations = vehicleNations;
        this.gameVersion = gameVersion;
    }

    public CommonInfoModel() {
    }

    public HashMap<String, AchievementInfoModel> getAchievments() {
        return achievments;
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
}
