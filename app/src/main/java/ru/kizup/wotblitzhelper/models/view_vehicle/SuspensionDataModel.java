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

public class SuspensionDataModel extends RealmObject
        implements VehicleModule {

    @SerializedName("tier")
    @Expose
    private Integer tier;
    @SerializedName("load_limit")
    @Expose
    private Integer loadLimit;
    @SerializedName("traverse_speed")
    @Expose
    private Integer traverseSpeed;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("module_id")
    @Expose
    @PrimaryKey
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getTier() {
        return tier;
    }

    public Integer getLoadLimit() {
        return loadLimit;
    }

    public Integer getTraverseSpeed() {
        return traverseSpeed;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }

    @Override
    public Type getType() {
        return Type.SUSPENSION;
    }
}
