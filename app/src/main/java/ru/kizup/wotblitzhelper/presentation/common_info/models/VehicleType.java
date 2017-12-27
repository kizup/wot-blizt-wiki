package ru.kizup.wotblitzhelper.presentation.common_info.models;

import android.support.annotation.NonNull;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehicleType {

    private String code;
    private String name;

    public VehicleType(@NonNull String code, @NonNull String name) {
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
        return "VehicleType{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
