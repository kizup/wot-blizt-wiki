package ru.kizup.wotblitzhelper.presentation.view.view_vehicle;

import java.util.List;

import ru.kizup.wotblitzhelper.base.BaseView;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ArmorDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.VehicleModule;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface IViewVehicleView extends BaseView {

    void showLoading();

    void hideLoading();

    void showInfoContainer();

    void loadVehicleImage(String imageUrl);

    void setVehicleName(String name);

    void setVehicleDescription(String description);

    void setVehicleNation(String nation);

    void setVehicleType(String type);

    void setVehicleTier(String tier);

    void showNextTanks(List<ShortVehicleInfoUIModel> models);

    void showNextTankViewScreen(int id);

    void setPercentageVehicleInfo(String firepower,
                                  String maneuverability,
                                  String protection,
                                  String shotEfficiency);

    void setHullArmorInfo(ArmorDataModel hullArmorDataModel);

    void setTurretArmorInfo(ArmorDataModel turretArmorDataModel);

    void setShowPremiumIcon(boolean show);

    void setModules(VehicleModule turret,
                    VehicleModule gun,
                    VehicleModule suspension,
                    VehicleModule engine);

    void showVehicleModules(List<? extends VehicleModule> modules);

    void hideVehicleModules();
}
