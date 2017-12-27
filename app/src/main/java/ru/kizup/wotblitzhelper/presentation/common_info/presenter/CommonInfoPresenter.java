package ru.kizup.wotblitzhelper.presentation.common_info.presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.kizup.wotblitzhelper.business.common_info.ICommonInfoInteractor;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.presentation.common_info.models.CommonInfo;
import ru.kizup.wotblitzhelper.presentation.common_info.view.ICommonInfoView;
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

    public CommonInfoPresenter(ICommonInfoInteractor commonInfoInteractor,
                               RxSchedulersAbs rxSchedulersAbs,
                               CommonInfoCache commonInfoCache) {
        mCommonInfoInteractor = commonInfoInteractor;
        mRxSchedulersAbs = rxSchedulersAbs;
        mCommonInfoCache = commonInfoCache;
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

    private void setCommonInfoToView(CommonInfo info) {
        mView.setGameVersion(info.getGameVersion());
        mView.setUpdatedAt(getFormattedDate(info.getUpdatedAt()));
        mView.setVehicleTypesCount(info.getVehicles().size());
    }

    private String getFormattedDate(Date date) {
        return mDateFormat.format(date);
    }

    private void handleFailureLoadCommonInfo(Throwable throwable) {
        mView.hideLoading();
        if (throwable instanceof FailureResponseException) {
            mView.showFailureResponseError(((FailureResponseException) throwable).getError().getDescription());
        } else {
            mView.showCommonError();
        }
    }

    private void handleSuccessLoadCommonInfo(CommonInfo info) {
        mCommonInfoCache.updateCommonInfo(info);
        setCommonInfoToView(info);
        mView.hideLoading();
    }
}
