package ru.kizup.wotblitzhelper.models.common_info;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kizup.wotblitzhelper.models.achievements.AchievementsModel;

/**
 * Created by: dpuzikov on 29.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementDao extends RealmObject {

    @PrimaryKey
    private String achievementId;
    private String code;
    private int order;
    private String description;
    private String image;
    private String imageBig;
    private String name;

    public AchievementDao() {
    }

    public AchievementDao(String code, AchievementsModel model) {
        this.code = code;
        name = model.getName();
        achievementId = model.getAchievementId();
        order = model.getOrder();
        description = model.getDescription();
        image = model.getImage();
        imageBig = model.getImageBig();
    }

    public String getAchievementId() {
        return achievementId;
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

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "AchievementDao{" +
                "achievementId='" + achievementId + '\'' +
                ", code='" + code + '\'' +
                ", order=" + order +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", imageBig='" + imageBig + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
