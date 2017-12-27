package ru.kizup.wotblitzhelper.presentation.common_info.models;

import java.util.Date;
import java.util.List;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfo {

    private String gameVersion;
    private Date updatedAt;
    private List<String> languages;
    private List<VehicleType> vehicles;
    private List<AchievementInfo> mAchievementInfos;

    public CommonInfo(String gameVersion, Date updatedAt, List<String> languages, List<VehicleType> vehicles, List<AchievementInfo> achievementInfos) {
        this.gameVersion = gameVersion;
        this.updatedAt = updatedAt;
        this.languages = languages;
        this.vehicles = vehicles;
        this.mAchievementInfos = achievementInfos;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<VehicleType> getVehicles() {
        return vehicles;
    }

    public List<AchievementInfo> getAchievementInfos() {
        return mAchievementInfos;
    }

    @Override
    public String toString() {
        return "CommonInfo{" +
                "gameVersion='" + gameVersion + '\'' +
                ", updatedAt=" + updatedAt +
                ", languages=" + languages +
                ", vehicles=" + vehicles +
                ", mAchievementInfos=" + mAchievementInfos +
                '}';
    }
}
