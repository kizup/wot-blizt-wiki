package ru.kizup.wotblitzhelper.business;

import android.support.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.models.Constants;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoDataModel;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.GunDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.GunUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModuleDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModuleUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ProfileDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ProfileUIModel;

/**
 * Created by: dpuzikov on 12.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class VehiclesMapper {

    public ShortVehicleInfoUIModel toShortUIModel(ShortVehicleInfoDataModel dataModel) {
        if (dataModel.getTankId() == null) return ShortVehicleInfoUIModel.empty();

        String cost = dataModel.getCost() == null
                ? String.valueOf(0)
                : (dataModel.getCost().getPriceCredit() != null
                ? String.valueOf(dataModel.getCost().getPriceCredit())
                : String.valueOf(dataModel.getCost().getPriceGold()));

        return new ShortVehicleInfoUIModel(
                dataModel.getTankId(),
                dataModel.getName(),
                dataModel.getDescription(),
                dataModel.getTier(),
                dataModel.getImages().getPreview(),
                cost,
                dataModel.getNation(),
                dataModel.getType(),
                dataModel.getPremium()
        );
    }

    public DetailVehicleUIModel toDetailUIModel(DetailVehicleDataModel dataModel,
                                                String nation,
                                                String type) {
        DetailVehicleUIModel model = new DetailVehicleUIModel(
                dataModel.getTankId(),
                dataModel.getDescription(),
                nation,
                dataModel.getImages(),
                dataModel.getCost(),
                toProfileUIModel(dataModel.getDefaultProfile()),
                dataModel.getTier(),
                type,
                dataModel.getName(),
                new ArrayList<>(dataModel.getEngines()),
                new ArrayList<>(dataModel.getSuspensions()),
                new ArrayList<>(dataModel.getGuns()),
                new ArrayList<>(dataModel.getTurrets()),
                new ArrayList<>(dataModel.getPrices()),
                new ArrayList<>(dataModel.getNextTanksList()),
                toUIModulesList(dataModel.getModulesList()),
                dataModel.getPremium() == null ? false : dataModel.getPremium()
        );
        model.setTypeDrawable(getVehicleTypeDrawable(dataModel.getType()));
        return model;
    }

    @DrawableRes
    private int getVehicleTypeDrawable(String type) {
        @DrawableRes int typeDrawable = 0;
        switch (type) {
            case Constants.VehicleTypeCodes.AT: {
                typeDrawable = R.drawable.ic_at_tank;
                break;
            }
            case Constants.VehicleTypeCodes.HT: {
                typeDrawable = R.drawable.ic_heavy_tank;
                break;
            }
            case Constants.VehicleTypeCodes.LT: {
                typeDrawable = R.drawable.ic_light_tank;
                break;
            }
            case Constants.VehicleTypeCodes.MT: {
                typeDrawable = R.drawable.ic_medium_tank;
                break;
            }
        }

        return typeDrawable;
    }

    private List<ModuleUIModel> toUIModulesList(List<ModuleDataModel> dataModels) {
        if (dataModels == null || dataModels.isEmpty()) return Collections.emptyList();

        List<ModuleUIModel> modules = new ArrayList<>(dataModels.size());
        for (ModuleDataModel dataModel : dataModels) {
            List<Integer> nextTanks = dataModel.getNextTanks() == null
                    ? Collections.emptyList()
                    : new ArrayList<>(dataModel.getNextTanks());
            List<Integer> nextModules = dataModel.getNextModules() == null
                    ? Collections.emptyList()
                    : new ArrayList<>(dataModel.getNextModules());
            modules.add(new ModuleUIModel(
                    dataModel.getModuleId(),
                    dataModel.getName(),
                    dataModel.getDefault(),
                    dataModel.getPriceXp(),
                    dataModel.getPriceCredit(),
                    dataModel.getType(),
                    nextTanks,
                    nextModules
            ));
        }
        return modules;
    }

    private GunUIModel toGunUIModel(GunDataModel dataModel, int id) {
        if (dataModel == null) return null;
        return new GunUIModel(
                dataModel.getMoveDownArc(),
                dataModel.getCaliber(),
                dataModel.getName(),
                dataModel.getWeight(),
                dataModel.getMoveUpArc(),
                dataModel.getFireRate(),
                dataModel.getClipReloadTime(),
                dataModel.getDispersion(),
                dataModel.getClipCapacity(),
                dataModel.getTraverseSpeed(),
                dataModel.getReloadTime(),
                dataModel.getTier(),
                dataModel.getAimTime(),
                id
        );
    }

    private ProfileUIModel toProfileUIModel(ProfileDataModel dataModel) {
        if (dataModel == null) return null;

        return new ProfileUIModel(
                dataModel.getWeight(),
                dataModel.getProfileId(),
                dataModel.getFirepower(),
                dataModel.getShotEfficiency(),
                dataModel.getGunId(),
                dataModel.getSignalRange(),
                dataModel.getSpeedForward(),
                dataModel.getBattleLevelRangeMin(),
                dataModel.getSpeedBackward(),
                dataModel.getEngine(),
                dataModel.getMaxAmmo(),
                dataModel.getBattleLevelRangeMax(),
                dataModel.getEngineId(),
                dataModel.getHp(),
                dataModel.getDefault(),
                dataModel.getProtection(),
                dataModel.getSuspension(),
                dataModel.getSuspensionId(),
                dataModel.getMaxWeight(),
                toGunUIModel(dataModel.getGun(), dataModel.getGunId()),
                dataModel.getTurretId(),
                dataModel.getTurret(),
                dataModel.getManeuverability(),
                dataModel.getHullWeight(),
                dataModel.getHullHp(),
                new ArrayList<>(dataModel.getShells()),
                new ArrayList<>(dataModel.getArmorsList())
        );
    }

}
