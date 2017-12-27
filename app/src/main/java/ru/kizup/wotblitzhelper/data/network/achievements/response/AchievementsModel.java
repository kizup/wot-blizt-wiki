package ru.kizup.wotblitzhelper.data.network.achievements.response;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsModel {

    @SerializedName("achievement_id")
    private String achievementId;
    @SerializedName("image")
    private String image;
    @SerializedName("order")
    private int order;
    @SerializedName("name")
    private String name;
    @SerializedName("section")
    private String section;

    public String getAchievementId() {
        return achievementId;
    }

    public String getImage() {
        return image;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public String getSection() {
        return section;
    }
}
