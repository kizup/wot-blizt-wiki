package ru.kizup.wotblitzhelper.models.vehicles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ShortVehicleInfoDataModel extends RealmObject {

    public static ShortVehicleInfoDataModel empty() {
        return new ShortVehicleInfoDataModel();
    }

    @SerializedName("tank_id")
    @Expose
    @PrimaryKey
    private Integer tankId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nation")
    @Expose
    private String nation;
    @SerializedName("tier")
    @Expose
    private Integer tier;
    @SerializedName("cost")
    @Expose
    private Cost cost;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("is_premium")
    @Expose
    private boolean isPremium;

    public Integer getTankId() {
        return tankId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return nation;
    }

    public Integer getTier() {
        return tier;
    }

    public Cost getCost() {
        return cost;
    }

    public Images getImages() {
        return images;
    }

    public String getType() {
        return type;
    }

    public boolean getPremium() {
        return isPremium;
    }
}
