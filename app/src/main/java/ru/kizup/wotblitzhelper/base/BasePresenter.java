package ru.kizup.wotblitzhelper.base;

/**
 * Created by: dpuzikov on 19.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface BasePresenter<V extends BaseView> {

    void bindView(V view);

    void unbindView();

}
