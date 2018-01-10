package ru.kizup.wotblitzhelper.models.view_vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by: dpuzikov on 10.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ModulesDataModel {

    @SerializedName("suspensions")
    @Expose
    private List<SuspensionDataModel> suspensions = null;
    @SerializedName("engines")
    @Expose
    private List<EngineDataModel> engines = null;
    @SerializedName("guns")
    @Expose
    private List<GunDataModel> guns = null;
    @SerializedName("turrets")
    @Expose
    private List<TurretDataModel> turrets = null;

    public List<SuspensionDataModel> getSuspensions() {
        return suspensions;
    }

    public List<EngineDataModel> getEngines() {
        return engines;
    }

    public List<GunDataModel> getGuns() {
        return guns;
    }

    public List<TurretDataModel> getTurrets() {
        return turrets;
    }
}
