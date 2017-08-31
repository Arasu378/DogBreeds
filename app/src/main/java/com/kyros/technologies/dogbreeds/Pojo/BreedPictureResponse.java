package com.kyros.technologies.dogbreeds.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kyros on 23-08-2017.
 */

public class BreedPictureResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private List<String> message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public  List<String> getMessage() {
        return message;
    }

    public void setMessage( List<String> message) {
        this.message = message;
    }

}
