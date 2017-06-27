package org.foodrev.androidretrofitexample.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dastechlabs on 6/26/17.
 */

public class JsonTest {
    @SerializedName("one")
    @Expose
    private String one;
    @SerializedName("key")
    @Expose
    private String key;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
