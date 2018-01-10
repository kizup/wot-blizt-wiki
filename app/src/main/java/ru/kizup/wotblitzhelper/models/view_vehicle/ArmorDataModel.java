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

    public static final int HULL_ARMOR = 0;
    public static final int TURRET_ARMOR = 1;

    public static final String HULL = "hull";
    public static final String TURRET = "turret";

    @SerializedName("front")
    @Expose
    private int front;
    @SerializedName("sides")
    @Expose
    private int sides;
    @SerializedName("rear")
    @Expose
    private int rear;

    // Бронирование (корпуса либо башни)
    private int type;

    public ArmorDataModel() {
    }

    public ArmorDataModel(int front, int sides, int rear, int type) {
        this.front = front;
        this.sides = sides;
        this.rear = rear;
        this.type = type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static int getHullArmor() {
        return HULL_ARMOR;
    }

    public static int getTurretArmor() {
        return TURRET_ARMOR;
    }

    public int getFront() {
        return front;
    }

    public int getSides() {
        return sides;
    }

    public int getRear() {
        return rear;
    }

    public int getType() {
        return type;
    }
}
