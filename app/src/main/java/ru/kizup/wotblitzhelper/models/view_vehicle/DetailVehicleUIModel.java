package ru.kizup.wotblitzhelper.models.view_vehicle;

import android.support.annotation.DrawableRes;

import java.util.List;

import ru.kizup.wotblitzhelper.models.vehicles.Cost;
import ru.kizup.wotblitzhelper.models.vehicles.Images;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class DetailVehicleUIModel {

    private Integer tankId;
    private String description;
    private String nation;
    private Images images;
    private Cost cost;
    private ProfileUIModel defaultProfile;
    private Integer tier;
    private String type;
    private String name;
    private boolean premium;
    private List<Integer> engines = null;
    private List<Integer> suspensions = null;
    private List<Integer> guns = null;
    private List<Integer> turrets = null;
    private List<PriceXp> prices = null;
    private List<NextTank> nextTanksList = null;
    private List<ModuleUIModel> modulesList = null;
    private @DrawableRes int typeDrawable;

    public DetailVehicleUIModel(Integer tankId,
                                String description,
                                String nation,
                                Images images,
                                Cost cost,
                                ProfileUIModel defaultProfile,
                                Integer tier,
                                String type,
                                String name,
                                List<Integer> engines,
                                List<Integer> suspensions,
                                List<Integer> guns,
                                List<Integer> turrets,
                                List<PriceXp> prices,
                                List<NextTank> nextTanksList,
                                List<ModuleUIModel> modulesList,
                                boolean premium) {
        this.tankId = tankId;
        this.description = description;
        this.nation = nation;
        this.images = images;
        this.cost = cost;
        this.defaultProfile = defaultProfile;
        this.tier = tier;
        this.type = type;
        this.name = name;
        this.engines = engines;
        this.suspensions = suspensions;
        this.guns = guns;
        this.turrets = turrets;
        this.prices = prices;
        this.nextTanksList = nextTanksList;
        this.modulesList = modulesList;
        this.premium = premium;
    }

    public void setTypeDrawable(@DrawableRes int typeDrawable) {
        this.typeDrawable = typeDrawable;
    }

    public Integer getTankId() {
        return tankId;
    }

    public String getDescription() {
        return description;
    }

    public String getNation() {
        return nation;
    }

    public Images getImages() {
        return images;
    }

    public Cost getCost() {
        return cost;
    }

    public ProfileUIModel getDefaultProfile() {
        return defaultProfile;
    }

    public Integer getTier() {
        return tier;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getEngines() {
        return engines;
    }

    public List<Integer> getSuspensions() {
        return suspensions;
    }

    public List<Integer> getGuns() {
        return guns;
    }

    public List<Integer> getTurrets() {
        return turrets;
    }

    public List<PriceXp> getPrices() {
        return prices;
    }

    public List<NextTank> getNextTanksList() {
        return nextTanksList;
    }

    public List<ModuleUIModel> getModulesList() {
        return modulesList;
    }

    public boolean isPremium() {
        return premium;
    }

    public @DrawableRes int getTypeDrawable() {
        return typeDrawable;
    }
}
