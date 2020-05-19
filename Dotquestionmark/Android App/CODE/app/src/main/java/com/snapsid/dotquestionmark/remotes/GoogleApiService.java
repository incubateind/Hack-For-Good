package com.snapsid.dotquestionmark.remotes;

import com.snapsid.dotquestionmark.models.MyPlaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GoogleApiService {
    @GET
    Call<MyPlaces> getNearByPlaces(@Url String url);

    @GET
    Call<MyPlaces> getMyNearByPlaces(@Url String url);
}
