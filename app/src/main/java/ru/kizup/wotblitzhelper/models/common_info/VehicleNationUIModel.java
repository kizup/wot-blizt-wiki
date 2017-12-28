package ru.kizup.wotblitzhelper.models.common_info;

import android.support.annotation.NonNull;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehicleNationUIModel {

    private String code;
    private String name;

    public VehicleNationUIModel(@NonNull String code, @NonNull String name) {
        this.code = code;
        this.name = name;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "VehicleNationUIModel{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
