package ru.kizup.wotblitzhelper.models.view_vehicle;

import io.realm.RealmObject;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class NextTank extends RealmObject {

    private String nextTankId;
    private Integer cost;

    public NextTank(String nextTankId, Integer cost) {
        this.nextTankId = nextTankId;
        this.cost = cost;
    }

    public NextTank() {
    }

    public String getNextTankId() {
        return nextTankId;
    }

    public Integer getCost() {
        return cost;
    }
}
