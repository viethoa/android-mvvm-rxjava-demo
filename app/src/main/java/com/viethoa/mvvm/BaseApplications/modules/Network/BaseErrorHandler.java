package com.viethoa.mvvm.BaseApplications.modules.Network;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by VietHoa on 19/01/16.
 */
public class BaseErrorHandler implements ErrorHandler {
    private static final String TAG = BaseErrorHandler.class.getSimpleName();

    public static class ErrorResponse {
        int code;
        Error error;
        String message;

        public static class Error {
            int code;
            String message;
        }
    }

    @Override
    public Throwable handleError(RetrofitError cause) {
        if (cause.isNetworkError()) {
            return new Exception("No network connection");
        }

        // Error message handling - return a simple error to Retrofit handlers..
        try {
            ErrorResponse errorResponse = (ErrorResponse) cause.getBodyAs(ErrorResponse.class);
            if (errorResponse == null) {
                return new Exception("Unknown Error");
            }

            if (errorResponse.error != null) {
                return new Exception(errorResponse.error.message);
            } else {
                return new Exception(errorResponse.message);
            }

        } catch (Exception e) {
            Response response = cause.getResponse();
            if (response == null) {
                return new Exception("Unknown error");
            }

            String reason = response.getReason();
            if (reason != null && !reason.isEmpty()) {
                return new Exception(cause.getResponse().getReason());
            } else {
                return new Exception("Request error " + cause.getResponse().getStatus());
            }
        }
    }


}
