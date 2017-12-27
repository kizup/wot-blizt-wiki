package ru.kizup.wotblitzhelper.data.network;

import ru.kizup.wotblitzhelper.base.BaseResponse;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class FailureResponseException extends Exception {

    private BaseResponse.Error mError;

    public FailureResponseException(BaseResponse.Error error) {
        super();
        mError = error;
    }

    public BaseResponse.Error getError() {
        return mError;
    }
}
