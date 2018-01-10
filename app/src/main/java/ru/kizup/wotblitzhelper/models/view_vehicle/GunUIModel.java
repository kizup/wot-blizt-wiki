package ru.kizup.wotblitzhelper.models.view_vehicle;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class GunUIModel
        implements VehicleModule{

    private Integer moveDownArc;
    private Integer caliber;
    private String name;
    private Integer weight;
    private Integer moveUpArc;
    private Double fireRate;
    private Double clipReloadTime;
    private Double dispersion;
    private Integer clipCapacity;
    private Double traverseSpeed;
    private Double reloadTime;
    private Integer tier;
    private Double aimTime;
    private int id;

    public GunUIModel(Integer moveDownArc,
                      Integer caliber,
                      String name,
                      Integer weight,
                      Integer moveUpArc,
                      Double fireRate,
                      Double clipReloadTime,
                      Double dispersion,
                      Integer clipCapacity,
                      Double traverseSpeed,
                      Double reloadTime,
                      Integer tier,
                      Double aimTime,
                      int id) {
        this.moveDownArc = moveDownArc;
        this.caliber = caliber;
        this.name = name;
        this.weight = weight;
        this.moveUpArc = moveUpArc;
        this.fireRate = fireRate;
        this.clipReloadTime = clipReloadTime;
        this.dispersion = dispersion;
        this.clipCapacity = clipCapacity;
        this.traverseSpeed = traverseSpeed;
        this.reloadTime = reloadTime;
        this.tier = tier;
        this.aimTime = aimTime;
        this.id = id;
    }

    public Integer getMoveDownArc() {
        return moveDownArc;
    }

    public Integer getCaliber() {
        return caliber;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Type getType() {
        return Type.GUN;
    }

    public int getWeight() {
        return weight;
    }

    public Integer getMoveUpArc() {
        return moveUpArc;
    }

    public Double getFireRate() {
        return fireRate;
    }

    public Double getClipReloadTime() {
        return clipReloadTime;
    }

    public Double getDispersion() {
        return dispersion;
    }

    public Integer getClipCapacity() {
        return clipCapacity;
    }

    public Double getTraverseSpeed() {
        return traverseSpeed;
    }

    public Double getReloadTime() {
        return reloadTime;
    }

    public int getTier() {
        return tier;
    }

    public Double getAimTime() {
        return aimTime;
    }
}
