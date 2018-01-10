package ru.kizup.wotblitzhelper.presentation.presenter.view_vehicle;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.business.view_vehicle.IViewVehicleInteractor;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.models.vehicles.ShortVehicleInfoUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ArmorDataModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.DetailVehicleUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.ModuleUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.NextTank;
import ru.kizup.wotblitzhelper.models.view_vehicle.ProfileUIModel;
import ru.kizup.wotblitzhelper.models.view_vehicle.VehicleModule;
import ru.kizup.wotblitzhelper.presentation.view.view_vehicle.IViewVehicleView;
import ru.kizup.wotblitzhelper.utils.RomanNumber;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 09.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ViewVehiclePresenter implements IViewVehiclePresenter {

    private IViewVehicleView mVehicleView;
    private RxSchedulersAbs mRxSchedulersAbs;
    private IViewVehicleInteractor mViewVehicleInteractor;
    private CompositeDisposable mCompositeDisposable;
    private ResponseValidator mResponseValidator;
    private int mVehicleId;
    private VehicleModule.Type mSelectedType;

    public ViewVehiclePresenter(RxSchedulersAbs rxSchedulersAbs,
                                IViewVehicleInteractor viewVehicleInteractor,
                                ResponseValidator validator) {
        mRxSchedulersAbs = rxSchedulersAbs;
        mViewVehicleInteractor = viewVehicleInteractor;
        mResponseValidator = validator;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void bindView(IViewVehicleView view) {
        mVehicleView = view;
    }

    @Override
    public void unbindView() {
        if (mCompositeDisposable != null) mCompositeDisposable.clear();
        mVehicleView = null;
    }

    @Override
    public void loadDetailVehicleInfo(int vehicleId) {
        mVehicleId = vehicleId;
        mCompositeDisposable.add(mViewVehicleInteractor.getVehicleDetailInformation(vehicleId)
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessLoadedDetailVehicleInfo,
                        this::handleErrorLoadDetailVehicleInfo));
    }

    @Override
    public void onNextVehicleClick(ShortVehicleInfoUIModel item) {
        mVehicleView.showNextTankViewScreen(item.getId());
    }

    @Override
    public void onVehicleModuleClick(VehicleModule module) {
        if (mSelectedType == null) {
            mSelectedType = module.getType();
        } else if (mSelectedType == module.getType()) {
            hideVehicleModulesContainer();
            return;
        }
        mSelectedType = module.getType();
        loadAllVehicleModulesByType(module);
    }

    @Override
    public void onVehicleInfoContainerClick() {
        if (mSelectedType == null) return;
        hideVehicleModulesContainer();
    }

    private void hideVehicleModulesContainer() {
        mVehicleView.hideVehicleModules();
        mSelectedType = null;
    }

    private void loadAllVehicleModulesByType(VehicleModule module) {
        Disposable disposable = mViewVehicleInteractor
                .getVehicleModulesByType(module.getType(), mVehicleId)
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessLoadedVehicleModules, this::handleErrorLoadVehicleModules);

        mCompositeDisposable.add(disposable);
    }

    private void loadNextTanks(List<NextTank> nextTanks) {
        Disposable disposable = mViewVehicleInteractor.getNextTanksShortInfo(nextTanks)
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessLoadedNextTanksInfo,
                        this::handleErrorLoadNextTanks);
        mCompositeDisposable.add(disposable);
    }

    private void handleSuccessLoadedVehicleModules(List<? extends VehicleModule> modules) {
        if (modules.isEmpty()) {
            hideVehicleModulesContainer();
        } else {
            mVehicleView.showVehicleModules(modules);
        }
    }

    private void handleErrorLoadVehicleModules(Throwable throwable) {
        throwable.printStackTrace();
        mVehicleView.hideVehicleModules();
    }

    private void handleSuccessLoadedNextTanksInfo(List<ShortVehicleInfoUIModel> models) {
        if (!models.isEmpty()) {
            mVehicleView.showNextTanks(models);
        }
    }

    private void handleErrorLoadNextTanks(Throwable throwable) {
        throwable.printStackTrace();
    }

    private void setVehicleProfileInfo(ProfileUIModel profile) {
        String firepower = String.valueOf(profile.getFirepower());
        String maneuverability = String.valueOf(profile.getManeuverability());
        String protection = String.valueOf(profile.getProtection());
        String shotEfficiency = String.valueOf(profile.getShotEfficiency());

        mVehicleView.setPercentageVehicleInfo(firepower, maneuverability, protection, shotEfficiency);

        ArmorDataModel turretArmorDataModel = null;
        ArmorDataModel hullArmorDataModel = null;

        for (ArmorDataModel armor : profile.getArmorsList()) {
            if (armor.getType() == ArmorDataModel.HULL_ARMOR) {
                hullArmorDataModel = armor;
            }
            if (armor.getType() == ArmorDataModel.TURRET_ARMOR) {
                turretArmorDataModel = armor;
            }
        }

        if (turretArmorDataModel != null) {
            mVehicleView.setTurretArmorInfo(turretArmorDataModel);
        }

        if (hullArmorDataModel != null) {
            mVehicleView.setHullArmorInfo(hullArmorDataModel);
        }

        mVehicleView.setModules(profile.getTurret(),
                profile.getGun(),
                profile.getSuspension(),
                profile.getEngine());
    }

    private void handleSuccessLoadedDetailVehicleInfo(DetailVehicleUIModel uiModel) {
        mVehicleView.hideLoading();
        mVehicleView.showInfoContainer();
        mVehicleView.setVehicleName(uiModel.getName());
        mVehicleView.setVehicleDescription(uiModel.getDescription());
        mVehicleView.setVehicleNation(uiModel.getNation());
        mVehicleView.setVehicleType(uiModel.getType());
        mVehicleView.setVehicleTier(RomanNumber.toRoman(uiModel.getTier()));
        mVehicleView.setShowPremiumIcon(uiModel.isPremium());

        setVehicleProfileInfo(uiModel.getDefaultProfile());

        if (uiModel.getImages() != null) {
            mVehicleView.loadVehicleImage(uiModel.getImages().getPreview());
        }

        loadNextTanks(uiModel.getNextTanksList());
    }

    private void handleErrorLoadDetailVehicleInfo(Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof FailureResponseException) {
            mVehicleView.showMessage(mResponseValidator.getErrorDescription(((FailureResponseException) throwable).getError()));
        }
        mVehicleView.hideLoading();
    }

}
