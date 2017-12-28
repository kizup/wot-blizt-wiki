package ru.kizup.wotblitzhelper.models.common_info;

import java.util.Date;
import java.util.List;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoUIModel {

    private String gameVersion;
    private Date updatedAt;
    private int languages;
    private int vehicleTypes;
    private int achievements;

    public CommonInfoUIModel(String gameVersion, Date updatedAt, int languages, int vehicles, int achievements) {
        this.gameVersion = gameVersion;
        this.updatedAt = updatedAt;
        this.languages = languages;
        this.vehicleTypes= vehicles;
        this.achievements = achievements;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public int getLanguages() {
        return languages;
    }

    public int getVehicles() {
        return vehicleTypes;
    }

    @Override
    public String toString() {
        return "CommonInfoUIModel{" +
                "gameVersion='" + gameVersion + '\'' +
                ", updatedAt=" + updatedAt +
                ", languages=" + languages +
                ", vehicles=" + vehicleTypes +
                ", mAchievementInfos=" + achievements +
                '}';
    }
}
