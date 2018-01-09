package ru.kizup.wotblitzhelper.models.crew_skills;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CrewSkillImage extends RealmObject {

    @SerializedName("large")
    @Expose
    private String large;

    public CrewSkillImage(String large) {
        this.large = large;
    }

    public CrewSkillImage() {
    }

    public String getLarge() {
        return large;
    }

}
