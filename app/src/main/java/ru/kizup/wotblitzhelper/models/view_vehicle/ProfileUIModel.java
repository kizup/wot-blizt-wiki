package ru.kizup.wotblitzhelper.models.view_vehicle;

import java.util.List;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ProfileUIModel {

    private Integer weight;
    private String profileId;
    private Integer firepower;
    private Integer shotEfficiency;
    private Integer gunId;
    private Integer signalRange;
    private Integer speedForward;
    private Integer battleLevelRangeMin;
    private Integer speedBackward;
    private EngineDataModel engine;
    private Integer maxAmmo;
    private Integer battleLevelRangeMax;
    private Integer engineId;
    private Integer hp;
    private Boolean isDefault;
    private Integer protection;
    private SuspensionDataModel suspension;
    private Integer suspensionId;
    private Integer maxWeight;
    private GunUIModel gun;
    private Integer turretId;
    private TurretDataModel turret;
    private Integer maneuverability;
    private Integer hullWeight;
    private Integer hullHp;
    private List<ShellDataModel> shells = null;
    private List<ArmorDataModel> armorsList = null;

    public ProfileUIModel(Integer weight,
                          String profileId,
                          Integer firepower,
                          Integer shotEfficiency,
                          Integer gunId,
                          Integer signalRange,
                          Integer speedForward,
                          Integer battleLevelRangeMin,
                          Integer speedBackward,
                          EngineDataModel engine,
                          Integer maxAmmo,
                          Integer battleLevelRangeMax,
                          Integer engineId,
                          Integer hp,
                          Boolean isDefault,
                          Integer protection,
                          SuspensionDataModel suspension,
                          Integer suspensionId,
                          Integer maxWeight,
                          GunUIModel gun,
                          Integer turretId,
                          TurretDataModel turret,
                          Integer maneuverability,
                          Integer hullWeight,
                          Integer hullHp,
                          List<ShellDataModel> shells,
                          List<ArmorDataModel> armorsList) {
        this.weight = weight;
        this.profileId = profileId;
        this.firepower = firepower;
        this.shotEfficiency = shotEfficiency;
        this.gunId = gunId;
        this.signalRange = signalRange;
        this.speedForward = speedForward;
        this.battleLevelRangeMin = battleLevelRangeMin;
        this.speedBackward = speedBackward;
        this.engine = engine;
        this.maxAmmo = maxAmmo;
        this.battleLevelRangeMax = battleLevelRangeMax;
        this.engineId = engineId;
        this.hp = hp;
        this.isDefault = isDefault;
        this.protection = protection;
        this.suspension = suspension;
        this.suspensionId = suspensionId;
        this.maxWeight = maxWeight;
        this.gun = gun;
        this.turretId = turretId;
        this.turret = turret;
        this.maneuverability = maneuverability;
        this.hullWeight = hullWeight;
        this.hullHp = hullHp;
        this.shells = shells;
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

    public GunUIModel getGun() {
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

    public List<ShellDataModel> getShells() {
        return shells;
    }

    public List<ArmorDataModel> getArmorsList() {
        return armorsList;
    }
}
