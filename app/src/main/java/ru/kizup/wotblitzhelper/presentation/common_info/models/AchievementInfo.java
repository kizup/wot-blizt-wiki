package ru.kizup.wotblitzhelper.presentation.common_info.models;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementInfo {

    private String name;
    private String code;
    private int order;

    public AchievementInfo(String name, String code, int order) {
        this.name = name;
        this.code = code;
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AchievementInfo{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", order=" + order +
                '}';
    }
}
