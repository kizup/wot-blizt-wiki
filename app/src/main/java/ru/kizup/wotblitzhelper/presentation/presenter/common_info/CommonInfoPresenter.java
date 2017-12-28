package ru.kizup.wotblitzhelper.presentation.presenter.common_info;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.kizup.wotblitzhelper.business.common_info.ICommonInfoInteractor;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.models.common_info.CommonInfoUIModel;
import ru.kizup.wotblitzhelper.presentation.view.common_info.ICommonInfoView;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class CommonInfoPresenter implements ICommonInfoPresenter {

    private final DateFormat mDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

    private ICommonInfoView mView;
    private ICommonInfoInteractor mCommonInfoInteractor;
    private RxSchedulersAbs mRxSchedulersAbs;
    private CommonInfoCache mCommonInfoCache;
    private CompositeDisposable mCompositeDisposable;
    private ResponseValidator mResponseValidator;

    public CommonInfoPresenter(ICommonInfoInteractor commonInfoInteractor,
                               RxSchedulersAbs rxSchedulersAbs,
                               CommonInfoCache commonInfoCache,
                               ResponseValidator validator) {
        mCommonInfoInteractor = commonInfoInteractor;
        mRxSchedulersAbs = rxSchedulersAbs;
        mCommonInfoCache = commonInfoCache;
        mResponseValidator = validator;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void bindView(ICommonInfoView view) {
        mView = view;
    }

    @Override
    public void unbindView() {
        mCompositeDisposable.clear();
        mView = null;
    }

    @Override
    public void loadCommonInfo() {
        if (mCommonInfoCache.isCacheExists()) {
            mView.hideLoading();
            setCommonInfoToView(mCommonInfoCache.getCommonInfo());
        } else {
            loadCommonInfoFromData();
        }
    }

    @Override
    public void clickAchievementsButton() {
        mView.showAchievementsScreen();
    }

    private void loadCommonInfoFromData() {
        mView.showLoading();
        Disposable disposable = mCommonInfoInteractor.getCommonInfo()
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessLoadCommonInfo, this::handleFailureLoadCommonInfo);
        mCompositeDisposable.add(disposable);
    }

    private void setCommonInfoToView(CommonInfoUIModel info) {
        mView.setGameVersion(info.getGameVersion());
        mView.setUpdatedAt(getFormattedDate(info.getUpdatedAt()));
        mView.setVehicleTypesCount(info.getVehicles());
    }

    private String getFormattedDate(Date date) {
        return mDateFormat.format(date);
    }

    private void handleFailureLoadCommonInfo(Throwable throwable) {
        mView.hideLoading();
        if (throwable instanceof FailureResponseException) {
            mView.showFailureResponseError(mResponseValidator.getErrorDescription(((FailureResponseException) throwable).getError()));
        } else {
            mView.showCommonError();
        }
    }

    private void handleSuccessLoadCommonInfo(CommonInfoUIModel info) {
        mCommonInfoCache.updateCommonInfo(info);
        setCommonInfoToView(info);
        mView.hideLoading();
    }
}
