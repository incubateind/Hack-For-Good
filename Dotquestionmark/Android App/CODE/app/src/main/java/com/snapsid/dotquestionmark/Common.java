package com.snapsid.dotquestionmark;

import com.snapsid.dotquestionmark.remotes.GoogleApiService;
import com.snapsid.dotquestionmark.remotes.RetrofitBuilder;

public class Common {
    private static final String BASE_URL = "https://maps.googleapis.com/";

    public static GoogleApiService getGoogleApiService() {
        return RetrofitBuilder.builder().create(GoogleApiService.class);
    }
}
