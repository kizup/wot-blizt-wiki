package ru.kizup.wotblitzhelper.models.crew_skills;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillUIModel {

    private String id;
    private String name;
    private String imageUrl;
    private String tip;
    private String effect;
    private String features;

    public CrewSkillUIModel(String id, String name, String imageUrl, String tip, String effect) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.tip = tip;
        this.effect = effect;
    }

    public CrewSkillUIModel(String id, String name, String imageUrl, String tip, String effect, String features) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.tip = tip;
        this.effect = effect;
        this.features = features;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTip() {
        return tip;
    }

    public String getEffect() {
        return effect;
    }

    public String getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "CrewSkillUIModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", tip='" + tip + '\'' +
                ", effect='" + effect + '\'' +
                ", features='" + features + '\'' +
                '}';
    }
}
