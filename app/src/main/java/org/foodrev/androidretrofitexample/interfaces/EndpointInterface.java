package org.foodrev.androidretrofitexample.interfaces;

import org.foodrev.androidretrofitexample.pojos.JsonTest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dastechlabs on 6/26/17.
 */

public interface EndpointInterface {

    @GET("key/value/{keyOne}/two")
    Call<JsonTest> getValue(@Path("keyOne") String keyOne);
}
