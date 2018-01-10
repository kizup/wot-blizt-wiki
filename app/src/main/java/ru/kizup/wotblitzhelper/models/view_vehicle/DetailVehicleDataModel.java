package ru.kizup.wotblitzhelper.models.view_vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import io.realm.RealmList;
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
    private RealmList<Integer> suspensions = new RealmList<>();
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("engines")
    @Expose
    private RealmList<Integer> engines = new RealmList<>();
    @SerializedName("prices_xp")
    @Expose
    @Ignore
    private HashMap<String, Integer> pricesXp;
    @SerializedName("next_tanks")
    @Expose
    @Ignore
    private HashMap<String, Integer> nextTanks;
    @SerializedName("modules_tree")
    @Expose
    @Ignore
    private HashMap<String, ModuleDataModel> modules;
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
    @SerializedName("default_profile")
    @Expose
    private ProfileDataModel defaultProfile;
    @SerializedName("tier")
    @Expose
    private Integer tier;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("guns")
    @Expose
    private RealmList<Integer> guns = new RealmList<>();
    @SerializedName("turrets")
    @Expose
    private RealmList<Integer> turrets = new RealmList<>();
    @SerializedName("name")
    @Expose
    private String name;

    // Data for realm, because it don't work with Maps
    private RealmList<PriceXp> prices = new RealmList<>();
    private RealmList<NextTank> nextTanksList = new RealmList<>();
    private RealmList<ModuleDataModel> modulesList = new RealmList<>();

    public void setPrices(RealmList<PriceXp> prices) {
        this.prices = prices;
    }

    public RealmList<ModuleDataModel> getModulesList() {
        return modulesList;
    }

    public void setModulesList(RealmList<ModuleDataModel> modulesList) {
        this.modulesList = modulesList;
    }

    public RealmList<NextTank> getNextTanksList() {
        return nextTanksList;
    }

    public void setNextTanksList(RealmList<NextTank> nextTanksList) {
        this.nextTanksList = nextTanksList;
    }

    public Integer getTankId() {
        return tankId;
    }

    public RealmList<Integer> getSuspensions() {
        return suspensions;
    }

    public String getDescription() {
        return description;
    }

    public RealmList<Integer> getEngines() {
        return engines;
    }

    public HashMap<String, Integer> getPricesXp() {
        return pricesXp;
    }

    public HashMap<String, Integer> getNextTanks() {
        return nextTanks;
    }

    public HashMap<String, ModuleDataModel> getModules() {
        return modules;
    }

    public String getNation() {
        return nation;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public Images getImages() {
        return images;
    }

    public Cost getCost() {
        return cost;
    }

    public Integer getTier() {
        return tier;
    }

    public String getType() {
        return type;
    }

    public RealmList<Integer> getGuns() {
        return guns;
    }

    public RealmList<Integer> getTurrets() {
        return turrets;
    }

    public Integer[] getTurretsArray() {
        Integer[] turretsArray = new Integer[turrets.size()];
        return turrets.toArray(turretsArray);
    }

    public Integer[] getGunsArray() {
        Integer[] gunsArray = new Integer[guns.size()];
        return guns.toArray(gunsArray);
    }

    public Integer[] getSuspensionsArray() {
        Integer[] suspensionsArray = new Integer[suspensions.size()];
        return suspensions.toArray(suspensionsArray);
    }

    public Integer[] getEnginesArray() {
        Integer[] enginesArray = new Integer[engines.size()];
        return engines.toArray(enginesArray);
    }

    public String getName() {
        return name;
    }

    public RealmList<PriceXp> getPrices() {
        return prices;
    }

    public ProfileDataModel getDefaultProfile() {
        return defaultProfile;
    }

}
