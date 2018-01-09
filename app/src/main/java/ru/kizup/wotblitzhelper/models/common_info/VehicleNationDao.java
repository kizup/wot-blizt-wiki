package ru.kizup.wotblitzhelper.models.common_info;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehicleNationDao extends RealmObject {

    @PrimaryKey
    private String code;
    private String name;

    public VehicleNationDao() {
    }

    public VehicleNationDao(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "VehicleNationDao{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
