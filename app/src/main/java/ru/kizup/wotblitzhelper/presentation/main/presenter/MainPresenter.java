package ru.kizup.wotblitzhelper.presentation.main.presenter;

import ru.kizup.wotblitzhelper.presentation.main.view.IMainView;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class MainPresenter implements IMainPresenter {

    private IMainView mView;

    @Override
    public void bindView(IMainView view) {
        mView = view;
    }

    @Override
    public void unbindView() {
        mView = null;
    }

    @Override
    public void clickOnCommonInfo() {
        mView.showCommonInfoScreen();
    }
}
