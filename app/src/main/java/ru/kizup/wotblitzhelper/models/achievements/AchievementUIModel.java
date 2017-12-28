package ru.kizup.wotblitzhelper.models.achievements;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementUIModel {

    private String name;
    private String image;
    private String imageBig;
    private String id;
    private String description;
    private int order;

    public AchievementUIModel(String name, String image, String imageBig, int order, String id, String description) {
        this.name = name;
        this.image = image;
        this.imageBig = imageBig;
        this.order = order;
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "AchievementUIModel{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", imageBig='" + imageBig + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", order=" + order +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getImageBig() {
        return imageBig;
    }

    public int getOrder() {
        return order;
    }

    public String getId() {
        return id;
    }

}
