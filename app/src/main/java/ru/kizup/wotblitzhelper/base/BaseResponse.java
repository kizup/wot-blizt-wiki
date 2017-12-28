package ru.kizup.wotblitzhelper.base;

import com.google.gson.annotations.SerializedName;

import ru.kizup.wotblitzhelper.models.Constants;

/**
 * Created by: dpuzikov on 27.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class BaseResponse<D> {

    @SerializedName("data")
    D data;
    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private Error error;

    public BaseResponse(String status, D data) {
        this.status = status;
        this.data = data;
    }

    public BaseResponse() {
    }

    public String getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return status.equalsIgnoreCase(Constants.OK_STATUS);
    }

    public Error getError() {
        return error;
    }

    public D getData() {
        return data;
    }

    public static class Error {

        @SerializedName("field")
        private String field;
        @SerializedName("message")
        private String message;
        @SerializedName("code")
        private int code;
        @SerializedName("value")
        private String value;

        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }

        public int getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

}
