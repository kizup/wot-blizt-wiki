package ru.kizup.wotblitzhelper.presentation.achievements.presenter;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.kizup.wotblitzhelper.business.achievements.IAchievementsInteractor;
import ru.kizup.wotblitzhelper.presentation.achievements.models.Achievement;
import ru.kizup.wotblitzhelper.presentation.achievements.view.IAchievementsView;
import ru.kizup.wotblitzhelper.utils.rx.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class AchievementsPresenter implements IAchievementsPresenter {

    private IAchievementsView mView;
    private IAchievementsInteractor mAchievementsInteractor;
    private RxSchedulersAbs mRxSchedulersAbs;
    private CompositeDisposable mCompositeDisposable;

    public AchievementsPresenter(IAchievementsInteractor achievementsInteractor,
                                 RxSchedulersAbs rxSchedulersAbs) {
        mAchievementsInteractor = achievementsInteractor;
        mRxSchedulersAbs = rxSchedulersAbs;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void bindView(IAchievementsView view) {
        mView = view;
    }

    @Override
    public void unbindView() {
        mCompositeDisposable.clear();
        mView = null;
    }

    @Override
    public void loadShortAchievementsInfo() {
        loadShortInfoFromData();
    }

    private void loadShortInfoFromData() {
        mView.showLoading();
        Disposable disposable = mAchievementsInteractor.getAchievementsShortInfo()
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessShortInfo, this::handleFailureShortInfo);
        mCompositeDisposable.add(disposable);
    }

    private void handleSuccessShortInfo(List<Achievement> achievements) {
        mView.hideLoading();
        mView.showLoadedAchievements(achievements);
    }

    private void handleFailureShortInfo(Throwable throwable) {
        mView.hideLoading();
    }

}
