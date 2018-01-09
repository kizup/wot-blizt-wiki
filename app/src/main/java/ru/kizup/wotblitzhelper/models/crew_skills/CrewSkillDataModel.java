package ru.kizup.wotblitzhelper.models.crew_skills;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillDataModel extends RealmObject {

    @SerializedName("skill_id")
    @Expose
    @PrimaryKey
    private String skillId;
    @SerializedName("features")
    @Expose
    private String features;
    @SerializedName("tip")
    @Expose
    private String tip;
    @SerializedName("effect")
    @Expose
    private String effect;
    @SerializedName("images")
    @Expose
    private CrewSkillImage images;
    @SerializedName("vehicle_type")
    @Expose
    private String vehicleType;
    @SerializedName("name")
    @Expose
    private String name;

    public CrewSkillDataModel() {
    }

    public CrewSkillDataModel(String skillId,
                              String features,
                              String tip,
                              String effect,
                              CrewSkillImage images,
                              String vehicleType,
                              String name) {
        this.skillId = skillId;
        this.features = features;
        this.tip = tip;
        this.effect = effect;
        this.images = images;
        this.vehicleType = vehicleType;
        this.name = name;
    }

    public String getSkillId() {
        return skillId;
    }

    public String getFeatures() {
        return features;
    }

    public String getTip() {
        return tip;
    }

    public String getEffect() {
        return effect;
    }

    public CrewSkillImage getImages() {
        return images;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getName() {
        return name;
    }

}
