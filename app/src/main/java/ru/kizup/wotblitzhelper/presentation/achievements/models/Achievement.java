package ru.kizup.wotblitzhelper.presentation.achievements.models;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class Achievement {

    private String name;
    private String image;
    private String section;
    private String id;
    private int order;

    public Achievement(String name, String image, String section, int order, String id) {
        this.name = name;
        this.image = image;
        this.section = section;
        this.order = order;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getSection() {
        return section;
    }

    public int getOrder() {
        return order;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", section='" + section + '\'' +
                ", order=" + order +
                ", id=" + id +
                '}';
    }
}
