package ru.kizup.wotblitzhelper.models.common_info;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by: dpuzikov on 11.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class Language extends RealmObject {

    @PrimaryKey
    private String code;
    private String name;

    public Language() {
    }

    public Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
