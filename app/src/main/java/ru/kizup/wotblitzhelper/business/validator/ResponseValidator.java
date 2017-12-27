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

    public BaseResponse.Error getErrorDescription(BaseResponse.Error error) {
        String description;
        switch (error.getCode()) {
            default: description = mContext.getString(R.string.unknown_error);
        }

        error.setDescription(description);
        return error;
    }

}
