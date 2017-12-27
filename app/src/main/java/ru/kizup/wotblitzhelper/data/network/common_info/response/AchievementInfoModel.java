package ru.kizup.wotblitzhelper.data.network.common_info.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementInfoModel {

    @SerializedName("name")
    private String name;
    @SerializedName("order")
    private int order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
