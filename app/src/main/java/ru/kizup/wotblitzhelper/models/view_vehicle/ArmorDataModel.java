package ru.kizup.wotblitzhelper.models.view_vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ArmorDataModel extends RealmObject {

    @SerializedName("turret")
    @Expose
    private TurretDataModel turret;
    @SerializedName("hull")
    @Expose
    private HullDataModel hull;

    public TurretDataModel getTurret() {
        return turret;
    }

    public void setTurret(TurretDataModel turret) {
        this.turret = turret;
    }

    public HullDataModel getHull() {
        return hull;
    }

    public void setHull(HullDataModel hull) {
        this.hull = hull;
    }

}
