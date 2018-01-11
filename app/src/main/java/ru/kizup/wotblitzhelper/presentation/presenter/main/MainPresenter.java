package ru.kizup.wotblitzhelper.presentation.presenter.main;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.business.main.IMainInteractor;
import ru.kizup.wotblitzhelper.data.db.EntityNotFoundException;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.data.network.InternetUnavailableException;
import ru.kizup.wotblitzhelper.presentation.view.main.IMainView;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class MainPresenter implements IMainPresenter {

    private IMainView mView;
    private IMainInteractor mMainInteractor;
    private CompositeDisposable mCompositeDisposable;
    private RxSchedulersAbs mRxSchedulersAbs;
    private Context mContext;
    private boolean isDatabaseUpdated;

    public MainPresenter(IMainInteractor mainInteractor,
                         RxSchedulersAbs rxSchedulersAbs,
                         Context context) {
        mMainInteractor = mainInteractor;
        mRxSchedulersAbs = rxSchedulersAbs;
        mContext = context;
    }

    @Override
    public void bindView(IMainView view) {
        mView = view;
        mCompositeDisposable = new CompositeDisposable();
        checkIsDatabaseUpdated();
    }

    @Override
    public void unbindView() {
        if (mCompositeDisposable != null) mCompositeDisposable.clear();
        mView = null;
    }

    @Override
    public void clickOnCommonInfo() {
        mView.showCommonInfoScreen();
    }

    @Override
    public void clickOnAchievements() {
        mView.showAchievementsScreen();
    }

    @Override
    public void clickOnCrewSkills() {
        mView.showCrewSkillsScreen();
    }

    @Override
    public void clickOnVehicles() {
        mView.showVehiclesScreen();
    }

    @Override
    public void updateDatabase() {
        mView.showUpdateDialog();
        Disposable disposable = mMainInteractor
                .updateDatabase()
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessUpdateDatabase, this::handleErrorUpdateDatabase);
        mCompositeDisposable.add(disposable);
    }

    private void checkIsDatabaseUpdated() {
        if (!isDatabaseUpdated) {
            Disposable disposable = mMainInteractor.checkDatabaseUpdated()
                    .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                    .subscribe(this::handleCheckIsDatabaseUpdated, this::handleErrorCheckIsDatabaseUpdated);
            mCompositeDisposable.add(disposable);
        }
    }

    private void handleCheckIsDatabaseUpdated(boolean isUpdated) {
        isDatabaseUpdated = isUpdated;
        if (!isDatabaseUpdated) {
            mView.showRequestUpdateDatabaseDialog();
        }
    }

    private void handleErrorCheckIsDatabaseUpdated(Throwable throwable) {
        isDatabaseUpdated = false;
        throwable.printStackTrace();
        if (throwable instanceof EntityNotFoundException) {
            handleCheckIsDatabaseUpdated(false);
        } else if (throwable instanceof InternetUnavailableException) {
            mView.showMessage(mContext.getString(R.string.error_internet_is_unavailable));
        } else if (throwable instanceof FailureResponseException) {
            mView.showMessage(((FailureResponseException) throwable).getError().getDescription());
        }
    }

    private void handleSuccessUpdateDatabase(boolean result) {
        isDatabaseUpdated = result;
        mView.hideUpdateDialog();
        mView.showMessage(mContext.getString(R.string.database_successfully_update));
    }

    private void handleErrorUpdateDatabase(Throwable throwable) {
        throwable.printStackTrace();
        mView.hideUpdateDialog();
    }

}
