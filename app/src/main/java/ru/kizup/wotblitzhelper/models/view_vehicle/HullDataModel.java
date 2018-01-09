package ru.kizup.wotblitzhelper.models.view_vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class HullDataModel extends RealmObject {

    @SerializedName("front")
    @Expose
    private Integer front;
    @SerializedName("sides")
    @Expose
    private Integer sides;
    @SerializedName("rear")
    @Expose
    private Integer rear;

    public Integer getFront() {
        return front;
    }

    public void setFront(Integer front) {
        this.front = front;
    }

    public Integer getSides() {
        return sides;
    }

    public void setSides(Integer sides) {
        this.sides = sides;
    }

    public Integer getRear() {
        return rear;
    }

    public void setRear(Integer rear) {
        this.rear = rear;
    }

}
