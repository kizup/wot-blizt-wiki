package ru.kizup.wotblitzhelper.models.common_info;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementSectionDataModel extends RealmObject {

    @SerializedName("name")
    private String name;
    @SerializedName("order")
    private int order;
    private String code;

    public AchievementSectionDataModel() {
    }

    public AchievementSectionDataModel(String name, int order, String code) {
        this.name = name;
        this.order = order;
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }
}
