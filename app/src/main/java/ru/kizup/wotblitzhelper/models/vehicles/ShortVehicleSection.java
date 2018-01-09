package ru.kizup.wotblitzhelper.models.vehicles;

import android.support.annotation.NonNull;

import ru.kizup.wotblitzhelper.models.common_info.VehicleNationUIModel;
import ru.kizup.wotblitzhelper.models.common_info.VehicleTypeUIModel;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ShortVehicleSection implements Comparable<ShortVehicleSection>{

    private FilterType filterType;
    private Integer tier;
    private VehicleNationUIModel nation;
    private VehicleTypeUIModel type;

    public ShortVehicleSection(Integer tier) {
        this.tier = tier;
        this.filterType = FilterType.BY_TIER;
    }

    public ShortVehicleSection(VehicleNationUIModel nation) {
        this.nation = nation;
        this.filterType = FilterType.BY_NATION;
    }

    public ShortVehicleSection(VehicleTypeUIModel type) {
        this.type = type;
        this.filterType = FilterType.BY_TYPE;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public int getTier() {
        return tier;
    }

    public VehicleNationUIModel getNation() {
        return nation;
    }

    public VehicleTypeUIModel getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortVehicleSection that = (ShortVehicleSection) o;

        if (filterType != that.filterType) return false;
        if (tier != null ? !tier.equals(that.tier) : that.tier != null) return false;
        if (nation != null ? !nation.equals(that.nation) : that.nation != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = filterType != null ? filterType.hashCode() : 0;
        result = 31 * result + (tier != null ? tier.hashCode() : 0);
        result = 31 * result + (nation != null ? nation.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(@NonNull ShortVehicleSection o) {
        if (this.tier != null && o.tier != null) return Integer.compare(this.tier, o.tier);
        if (this.nation != null && o.nation != null) return this.nation.compareTo(o.nation);
        if (this.type != null && o.type != null) return this.type.compareTo(o.type);
        return 0;
    }
}
