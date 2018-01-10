package ru.kizup.wotblitzhelper.models.view_vehicle;

import io.realm.RealmObject;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class PriceXp extends RealmObject {

    private String parentId;
    private Integer cost;

    public PriceXp(String parentId, Integer cost) {
        this.parentId = parentId;
        this.cost = cost;
    }

    public PriceXp() {
    }

    public Integer getCost() {
        return cost;
    }

    public String getParentId() {
        return parentId;
    }
}
