package ru.kizup.wotblitzhelper.models.view_vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ProfileDataModel extends RealmObject {

    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("profile_id")
    @Expose
    private String profileId;
    @SerializedName("firepower")
    @Expose
    private Integer firepower;
    @SerializedName("shot_efficiency")
    @Expose
    private Integer shotEfficiency;
    @SerializedName("gun_id")
    @Expose
    private Integer gunId;
    @SerializedName("signal_range")
    @Expose
    private Integer signalRange;
    @SerializedName("shells")
    @Expose
    private RealmList<ShellDataModel> shells = null;
    @SerializedName("armor")
    @Expose
    @Ignore
    private HashMap<String, ArmorDataModel> armor;
    @SerializedName("speed_forward")
    @Expose
    private Integer speedForward;
    @SerializedName("battle_level_range_min")
    @Expose
    private Integer battleLevelRangeMin;
    @SerializedName("speed_backward")
    @Expose
    private Integer speedBackward;
    @SerializedName("engine")
    @Expose
    private EngineDataModel engine;
    @SerializedName("max_ammo")
    @Expose
    private Integer maxAmmo;
    @SerializedName("battle_level_range_max")
    @Expose
    private Integer battleLevelRangeMax;
    @SerializedName("engine_id")
    @Expose
    private Integer engineId;
    @SerializedName("hp")
    @Expose
    private Integer hp;
    @SerializedName("is_default")
    @Expose
    private Boolean isDefault;
    @SerializedName("protection")
    @Expose
    private Integer protection;
    @SerializedName("suspension")
    @Expose
    private SuspensionDataModel suspension;
    @SerializedName("suspension_id")
    @Expose
    private Integer suspensionId;
    @SerializedName("max_weight")
    @Expose
    private Integer maxWeight;
    @SerializedName("gun")
    @Expose
    private GunDataModel gun;
    @SerializedName("turret_id")
    @Expose
    private Integer turretId;
    @SerializedName("turret")
    @Expose
    private TurretDataModel turret;
    @SerializedName("maneuverability")
    @Expose
    private Integer maneuverability;
    @SerializedName("hull_weight")
    @Expose
    private Integer hullWeight;
    @SerializedName("hull_hp")
    @Expose
    private Integer hullHp;

    private RealmList<ArmorDataModel> armorsList = null;

    public void setArmorsList(RealmList<ArmorDataModel> armorsList) {
        this.armorsList = armorsList;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getProfileId() {
        return profileId;
    }

    public Integer getFirepower() {
        return firepower;
    }

    public Integer getShotEfficiency() {
        return shotEfficiency;
    }

    public Integer getGunId() {
        return gunId;
    }

    public Integer getSignalRange() {
        return signalRange;
    }

    public RealmList<ShellDataModel> getShells() {
        return shells;
    }

    public HashMap<String, ArmorDataModel> getArmor() {
        return armor;
    }

    public Integer getSpeedForward() {
        return speedForward;
    }

    public Integer getBattleLevelRangeMin() {
        return battleLevelRangeMin;
    }

    public Integer getSpeedBackward() {
        return speedBackward;
    }

    public EngineDataModel getEngine() {
        return engine;
    }

    public Integer getMaxAmmo() {
        return maxAmmo;
    }

    public Integer getBattleLevelRangeMax() {
        return battleLevelRangeMax;
    }

    public Integer getEngineId() {
        return engineId;
    }

    public Integer getHp() {
        return hp;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public Integer getProtection() {
        return protection;
    }

    public SuspensionDataModel getSuspension() {
        return suspension;
    }

    public Integer getSuspensionId() {
        return suspensionId;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public GunDataModel getGun() {
        return gun;
    }

    public Integer getTurretId() {
        return turretId;
    }

    public TurretDataModel getTurret() {
        return turret;
    }

    public Integer getManeuverability() {
        return maneuverability;
    }

    public Integer getHullWeight() {
        return hullWeight;
    }

    public Integer getHullHp() {
        return hullHp;
    }

    public RealmList<ArmorDataModel> getArmorsList() {
        return armorsList;
    }
}
