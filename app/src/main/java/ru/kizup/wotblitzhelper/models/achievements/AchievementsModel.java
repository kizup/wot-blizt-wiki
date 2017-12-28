package ru.kizup.wotblitzhelper.models.achievements;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import ru.kizup.wotblitzhelper.models.achievements.AchievementOptionDataModel;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsModel {

    @SerializedName("achievement_id")
    private String achievementId;
    @SerializedName("order")
    private int order;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("image_big")
    private String imageBig;
    @SerializedName("name")
    private String name;

    public String getAchievementId() {
        return achievementId;
    }

    public int getOrder() {
        return order;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getImageBig() {
        return imageBig;
    }

    public String getName() {
        return name;
    }
}
