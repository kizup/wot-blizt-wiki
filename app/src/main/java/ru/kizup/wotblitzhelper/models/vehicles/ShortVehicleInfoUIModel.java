package ru.kizup.wotblitzhelper.models.vehicles;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ShortVehicleInfoUIModel implements Comparable<ShortVehicleInfoUIModel> {

    public static ShortVehicleInfoUIModel empty() {
        return new ShortVehicleInfoUIModel(ModelType.EMPTY);
    }

    public static ShortVehicleInfoUIModel header() {
        return new ShortVehicleInfoUIModel(ModelType.HEADER);
    }

    public enum ModelType {

        EMPTY,                  // Пустой объект
        HEADER,                 // Заголовок в списке (изображение типа техники)
        VEHICLE                 // Объект с техникой

    }

    private String name;
    private String description;
    private String smallImageUrl;
    private String cost;
    private String nationCode;
    private String typeCode;
    private int tier;
    private int id;
    private boolean isPremium;
    private ModelType modelType;
    private @DrawableRes int typeDrawable;

    public ShortVehicleInfoUIModel(int id,
                                   String name,
                                   String description,
                                   int tier,
                                   String smallImageUrl,
                                   String cost,
                                   String nationCode,
                                   String typeCode,
                                   boolean isPremium) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tier = tier;
        this.smallImageUrl = smallImageUrl;
        this.cost = cost;
        this.isPremium = isPremium;
        this.nationCode = nationCode;
        this.typeCode = typeCode;
        this.modelType = ModelType.VEHICLE;
    }

    public ShortVehicleInfoUIModel(ModelType modelType) {
        this.modelType = modelType;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public int getTypeDrawable() {
        return typeDrawable;
    }

    public void setTypeDrawable(int typeDrawable) {
        this.typeDrawable = typeDrawable;
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

    public int getTier() {
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

    public String getNationCode() {
        return nationCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public boolean isEmpty() {
        return name == null
                && description == null
                && smallImageUrl == null
                && cost == null
                && nationCode == null
                && typeCode == null;
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

    @Override
    public int compareTo(@NonNull ShortVehicleInfoUIModel o) {
        return tier - o.tier;
    }
}
