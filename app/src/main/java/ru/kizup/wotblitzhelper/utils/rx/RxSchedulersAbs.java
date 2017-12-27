package ru.kizup.wotblitzhelper.utils.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public abstract class RxSchedulersAbs {

    public abstract Scheduler io();
    public abstract Scheduler ui();
    public abstract Scheduler computation();

    public <T> ObservableTransformer<T, T> getIOToMainTransformer() {
        return obj -> obj.subscribeOn(io())
                .observeOn(ui());
    }

    public <T> SingleTransformer<T, T> getIOToMainTransformerSingle() {
        return upstream -> upstream.subscribeOn(io())
                .observeOn(ui());
    }

    public <T> ObservableTransformer<T, T> getComputationToMainTransformer() {
        return upstream -> upstream.subscribeOn(computation())
                .observeOn(ui());
    }

    public <T> SingleTransformer<T, T> getComputationToMainSingleTransformer() {
        return upstream -> upstream.subscribeOn(computation())
                .observeOn(ui());
    }

}
