package ru.kizup.wotblitzhelper.models.view_vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import ru.kizup.wotblitzhelper.models.vehicles.Cost;
import ru.kizup.wotblitzhelper.models.vehicles.Images;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class DetailVehicleDataModel extends RealmObject {

    public static DetailVehicleDataModel empty() {
        return new DetailVehicleDataModel();
    }

    @SerializedName("tank_id")
    @Expose
    @PrimaryKey
    private Integer tankId;
    @SerializedName("suspensions")
    @Expose
    @Ignore
    private List<Integer> suspensions = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("engines")
    @Expose
    @Ignore
    private List<Integer> engines = null;
//    @SerializedName("prices_xp")
//    @Expose
//    private PricesXp pricesXp;
//    @SerializedName("next_tanks")
//    @Expose
//    private NextTanks nextTanks;
//    @SerializedName("modules_tree")
//    @Expose
//    private ModulesTree modulesTree;
    @SerializedName("nation")
    @Expose
    private String nation;
    @SerializedName("is_premium")
    @Expose
    private Boolean isPremium;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("cost")
    @Expose
    private Cost cost;
//    @SerializedName("default_profile")
//    @Expose
//    private DefaultProfile defaultProfile;
    @SerializedName("tier")
    @Expose
    private Integer tier;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("guns")
    @Expose
    @Ignore
    private List<Integer> guns = null;
    @SerializedName("turrets")
    @Expose
    @Ignore
    private List<Integer> turrets = null;
    @SerializedName("name")
    @Expose
    private String name;

    public List<Integer> getSuspensions() {
        return suspensions;
    }

    public void setSuspensions(List<Integer> suspensions) {
        this.suspensions = suspensions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getEngines() {
        return engines;
    }

    public void setEngines(List<Integer> engines) {
        this.engines = engines;
    }

//    public PricesXp getPricesXp() {
//        return pricesXp;
//    }
//
//    public void setPricesXp(PricesXp pricesXp) {
//        this.pricesXp = pricesXp;
//    }
//
//    public NextTanks getNextTanks() {
//        return nextTanks;
//    }
//
//    public void setNextTanks(NextTanks nextTanks) {
//        this.nextTanks = nextTanks;
//    }

//    public ModulesTree getModulesTree() {
//        return modulesTree;
//    }
//
//    public void setModulesTree(ModulesTree modulesTree) {
//        this.modulesTree = modulesTree;
//    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Boolean getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(Boolean isPremium) {
        this.isPremium = isPremium;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

//    public DefaultProfile getDefaultProfile() {
//        return defaultProfile;
//    }
//
//    public void setDefaultProfile(DefaultProfile defaultProfile) {
//        this.defaultProfile = defaultProfile;
//    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getGuns() {
        return guns;
    }

    public void setGuns(List<Integer> guns) {
        this.guns = guns;
    }

    public List<Integer> getTurrets() {
        return turrets;
    }

    public void setTurrets(List<Integer> turrets) {
        this.turrets = turrets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
