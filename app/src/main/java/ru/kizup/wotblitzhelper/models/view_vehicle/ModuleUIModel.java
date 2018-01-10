package ru.kizup.wotblitzhelper.models.view_vehicle;

import java.util.List;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ModuleUIModel {

    private Integer moduleId;
    private String name;
    private boolean isDefault;
    private Integer priceXp;
    private Integer priceCredit;
    private String type;
    private List<Integer> nextTanks;
    private List<Integer> nextModules;

    public ModuleUIModel(Integer moduleId,
                         String name,
                         boolean isDefault,
                         Integer priceXp,
                         Integer priceCredit,
                         String type,
                         List<Integer> nextTanks,
                         List<Integer> nextModules) {
        this.moduleId = moduleId;
        this.name = name;
        this.isDefault = isDefault;
        this.priceXp = priceXp;
        this.priceCredit = priceCredit;
        this.type = type;
        this.nextTanks = nextTanks;
        this.nextModules = nextModules;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public String getName() {
        return name;
    }

    public boolean isDefault() {
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

    public List<Integer> getNextTanks() {
        return nextTanks;
    }

    public List<Integer> getNextModules() {
        return nextModules;
    }
}
