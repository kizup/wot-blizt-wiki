package ru.kizup.wotblitzhelper.models.vehicles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class Cost extends RealmObject {

    @SerializedName("price_credit")
    @Expose
    private Integer priceCredit;
    @SerializedName("price_gold")
    @Expose
    private Integer priceGold;

    public Integer getPriceCredit() {
        return priceCredit;
    }

    public void setPriceCredit(Integer priceCredit) {
        this.priceCredit = priceCredit;
    }

    public Integer getPriceGold() {
        return priceGold;
    }

    public void setPriceGold(Integer priceGold) {
        this.priceGold = priceGold;
    }

}
