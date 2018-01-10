package ru.kizup.wotblitzhelper.models.view_vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ShellDataModel extends RealmObject {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("penetration")
    @Expose
    private Integer penetration;
    @SerializedName("damage")
    @Expose
    private Integer damage;

    public String getType() {
        return type;
    }

    public Integer getPenetration() {
        return penetration;
    }

    public Integer getDamage() {
        return damage;
    }
}
