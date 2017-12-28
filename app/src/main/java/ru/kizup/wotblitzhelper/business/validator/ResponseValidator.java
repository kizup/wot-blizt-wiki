package ru.kizup.wotblitzhelper.business.validator;

import android.content.Context;

import io.reactivex.Single;
import ru.kizup.wotblitzhelper.R;
import ru.kizup.wotblitzhelper.base.BaseResponse;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class ResponseValidator {

    private Context mContext;

    public ResponseValidator(Context context) {
        mContext = context;
    }

    public String getErrorDescription(BaseResponse.Error error) {
        String description;
        switch (error.getMessage()) {
            case "REQUEST_LIMIT_EXCEEDED": {
                description = mContext.getString(R.string.error_request_limit_exceeded);
                break;
            }
            default: description = getDefaultErrorDescription();
        }
        return description;
    }

    public String getDefaultErrorDescription() {
        return mContext.getString(R.string.unknown_error);
    }

}
