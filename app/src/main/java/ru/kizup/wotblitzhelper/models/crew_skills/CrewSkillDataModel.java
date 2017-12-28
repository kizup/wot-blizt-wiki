package ru.kizup.wotblitzhelper.models.crew_skills;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by: dpuzikov on 28.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillDataModel {

    @SerializedName("skill_id")
    @Expose
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
    private Images images;
    @SerializedName("vehicle_type")
    @Expose
    private String vehicleType;
    @SerializedName("name")
    @Expose
    private String name;

    public CrewSkillDataModel() {
    }

    public CrewSkillDataModel(String skillId, String features, String tip, String effect, Images images, String vehicleType, String name) {
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

    public Images getImages() {
        return images;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getName() {
        return name;
    }

    public static class Images {

        @SerializedName("large")
        @Expose
        private String large;

        public Images(String large) {
            this.large = large;
        }

        public Images() {
        }

        public String getLarge() {
            return large;
        }
    }

}
