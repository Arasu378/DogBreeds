package com.kyros.technologies.dogbreeds.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kyros.technologies.dogbreeds.Interface.PojoInterface;
import com.kyros.technologies.dogbreeds.Pojo.RandomPictureResponse;
import com.kyros.technologies.dogbreeds.R;
import com.kyros.technologies.dogbreeds.ServiceHandler.ApiClient;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kyros on 23-08-2017.
 */

public class SplashActivity extends AppCompatActivity {
    private ImageView random_image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash);
        random_image=(ImageView)findViewById(R.id.random_image);

        PojoInterface apiService =
                ApiClient.getRetrofit().create(PojoInterface.class);
        Call<RandomPictureResponse> call = apiService.getRandomBreedImage();

        call.enqueue(new Callback<RandomPictureResponse>() {
            @Override
            public void onResponse(Call<RandomPictureResponse> call, Response<RandomPictureResponse> response) {
                RandomPictureResponse pictureResponse=response.body();
                String Url=pictureResponse.getMessage();
                if(Url!=null){
                    try{
                        Picasso.with(getApplicationContext()).load(Url).into(random_image);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                        SplashActivity.this.finish();
                    }
                }, 2000);
            }

            @Override
            public void onFailure(Call<RandomPictureResponse> call, Throwable t) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                        SplashActivity.this.finish();
                    }
                }, 2000);
                try{
                    t.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });


    }

}
