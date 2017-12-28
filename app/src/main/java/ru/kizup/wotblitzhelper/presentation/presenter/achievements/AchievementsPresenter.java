package ru.kizup.wotblitzhelper.presentation.presenter.achievements;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.kizup.wotblitzhelper.business.achievements.IAchievementsInteractor;
import ru.kizup.wotblitzhelper.business.validator.ResponseValidator;
import ru.kizup.wotblitzhelper.data.network.FailureResponseException;
import ru.kizup.wotblitzhelper.models.achievements.AchievementUIModel;
import ru.kizup.wotblitzhelper.presentation.view.achievements.IAchievementsView;
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
    private ResponseValidator mResponseValidator;
    private CompositeDisposable mCompositeDisposable;

    public AchievementsPresenter(IAchievementsInteractor achievementsInteractor,
                                 RxSchedulersAbs rxSchedulersAbs,
                                 ResponseValidator validator) {
        mAchievementsInteractor = achievementsInteractor;
        mRxSchedulersAbs = rxSchedulersAbs;
        mResponseValidator = validator;
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

    @Override
    public void onAchievementClick(AchievementUIModel model) {
        mView.showDetailAchievementDialog(model);
    }

    private void loadShortInfoFromData() {
        mView.showLoading();
        Disposable disposable = mAchievementsInteractor.getAchievementsShortInfo()
                .compose(mRxSchedulersAbs.getIOToMainTransformerSingle())
                .subscribe(this::handleSuccessShortInfo, this::handleFailureShortInfo);
        mCompositeDisposable.add(disposable);
    }

    private void handleSuccessShortInfo(List<AchievementUIModel> achievementUIModels) {
        mView.hideLoading();
        mView.showLoadedAchievements(achievementUIModels);
    }

    private void handleFailureShortInfo(Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof FailureResponseException) {
            mView.showMessage(mResponseValidator.getErrorDescription(((FailureResponseException) throwable).getError()));
        }
        mView.hideLoading();
    }

}
