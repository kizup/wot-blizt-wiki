package ru.kizup.wotblitzhelper.models.vehicles;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ShortVehicleInfoUIModel {

    private String name;
    private String description;
    private String tier;
    private String smallImageUrl;
    private String cost;
    private int id;
    private boolean isPremium;

    public ShortVehicleInfoUIModel(int id,
                                   String name,
                                   String description,
                                   String tier,
                                   String smallImageUrl,
                                   String cost,
                                   boolean isPremium) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tier = tier;
        this.smallImageUrl = smallImageUrl;
        this.cost = cost;
        this.isPremium = isPremium;
    }

    public ShortVehicleInfoUIModel() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTier() {
        return tier;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public String getCost() {
        return cost;
    }

    public boolean isPremium() {
        return isPremium;
    }

    @Override
    public String toString() {
        return "ShortVehicleInfoUIModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tier='" + tier + '\'' +
                ", smallImageUrl='" + smallImageUrl + '\'' +
                ", cost='" + cost + '\'' +
                ", id='" + id + '\'' +
                ", isPremium=" + isPremium +
                '}';
    }
}
