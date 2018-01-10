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

public class TurretDataModel extends RealmObject
        implements VehicleModule {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("view_range")
    @Expose
    private Integer viewRange;
    @SerializedName("traverse_left_arc")
    @Expose
    private Integer traverseLeftArc;
    @SerializedName("hp")
    @Expose
    private Integer hp;
    @SerializedName("traverse_speed")
    @Expose
    private Integer traverseSpeed;
    @SerializedName("tier")
    @Expose
    private Integer tier;
    @SerializedName("traverse_right_arc")
    @Expose
    private Integer traverseRightArc;
    @SerializedName("module_id")
    @Expose
    @PrimaryKey
    private int id;
    private int tankId;

    public void setId(int id) {
        this.id = id;
    }

    public void setTankId(int tankId) {
        this.tankId = tankId;
    }

    public int getTankId() {
        return tankId;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public Integer getViewRange() {
        return viewRange;
    }

    public Integer getTraverseLeftArc() {
        return traverseLeftArc;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getTraverseSpeed() {
        return traverseSpeed;
    }

    public int getTier() {
        return tier;
    }

    public Integer getTraverseRightArc() {
        return traverseRightArc;
    }

    public int getId() {
        return id;
    }

    @Override
    public Type getType() {
        return Type.TURRET;
    }
}
