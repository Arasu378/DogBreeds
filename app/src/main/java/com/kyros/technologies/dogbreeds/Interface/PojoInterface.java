package com.kyros.technologies.dogbreeds.Interface;

import com.kyros.technologies.dogbreeds.Pojo.BreedAllListResponse;
import com.kyros.technologies.dogbreeds.Pojo.BreedListResponse;
import com.kyros.technologies.dogbreeds.Pojo.BreedPictureResponse;
import com.kyros.technologies.dogbreeds.Pojo.RandomPictureResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kyros on 23-08-2017.
 */

public interface PojoInterface {
    @GET("/api/breeds/list")
    Call<BreedListResponse> getBreedAllListresponse();
    @GET("/api/breeds/image/random")
    Call<RandomPictureResponse> getRandomBreedImage();
    @GET("/api/breed/{breed}/images")
    Call<BreedPictureResponse>getBreedPictures(@Path("breed")String breed);
}
