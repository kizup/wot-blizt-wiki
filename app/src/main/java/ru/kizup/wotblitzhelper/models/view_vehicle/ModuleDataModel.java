package ru.kizup.wotblitzhelper.models.view_vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ModuleDataModel extends RealmObject {

    @SerializedName("module_id")
    @Expose
    private Integer moduleId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("next_modules")
    @Expose
    private RealmList<Integer> nextModules;
    @SerializedName("next_tanks")
    @Expose
    private RealmList<Integer> nextTanks;
    @SerializedName("is_default")
    @Expose
    private Boolean isDefault;
    @SerializedName("price_xp")
    @Expose
    private Integer priceXp;
    @SerializedName("price_credit")
    @Expose
    private Integer priceCredit;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getModuleId() {
        return moduleId;
    }

    public String getName() {
        return name;
    }

    public RealmList<Integer> getNextModules() {
        return nextModules;
    }

    public RealmList<Integer> getNextTanks() {
        return nextTanks;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public Integer getPriceXp() {
        return priceXp;
    }

    public Integer getPriceCredit() {
        return priceCredit;
    }

    public String getType() {
        return type;
    }
}
