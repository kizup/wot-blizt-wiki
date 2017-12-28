package ru.kizup.wotblitzhelper.models.achievements;

import com.google.gson.annotations.SerializedName;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementOptionDataModel {

    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("image_big")
    private String imageBig;
    @SerializedName("name")
    private String name;

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
