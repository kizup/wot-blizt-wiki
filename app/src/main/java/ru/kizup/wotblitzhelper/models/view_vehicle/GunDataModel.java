package ru.kizup.wotblitzhelper.models.view_vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class GunDataModel extends RealmObject implements VehicleModule {

    @SerializedName("move_down_arc")
    @Expose
    private Integer moveDownArc;
    @SerializedName("caliber")
    @Expose
    private Integer caliber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("move_up_arc")
    @Expose
    private Integer moveUpArc;
    @SerializedName("fire_rate")
    @Expose
    private Double fireRate;
    @SerializedName("clip_reload_time")
    @Expose
    private Double clipReloadTime;
    @SerializedName("dispersion")
    @Expose
    private Double dispersion;
    @SerializedName("clip_capacity")
    @Expose
    private Integer clipCapacity;
    @SerializedName("traverse_speed")
    @Expose
    private Double traverseSpeed;
    @SerializedName("reload_time")
    @Expose
    private Double reloadTime;
    @SerializedName("tier")
    @Expose
    private Integer tier;
    @SerializedName("aim_time")
    @Expose
    private Double aimTime;
    @SerializedName("module_id")
    @Expose
    @PrimaryKey
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public Type getType() {
        return Type.GUN;
    }

    public Integer getMoveDownArc() {
        return moveDownArc;
    }

    public Integer getCaliber() {
        return caliber;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public Integer getMoveUpArc() {
        return moveUpArc;
    }

    public Double getFireRate() {
        return fireRate;
    }

    public Double getClipReloadTime() {
        return clipReloadTime;
    }

    public Double getDispersion() {
        return dispersion;
    }

    public Integer getClipCapacity() {
        return clipCapacity;
    }

    public Double getTraverseSpeed() {
        return traverseSpeed;
    }

    public Double getReloadTime() {
        return reloadTime;
    }

    public int getTier() {
        return tier;
    }

    public Double getAimTime() {
        return aimTime;
    }
}
