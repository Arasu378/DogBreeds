package com.kyros.technologies.dogbreeds.Activity;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyros.technologies.dogbreeds.Adapters.AllBreedsAdapter;
import com.kyros.technologies.dogbreeds.Adapters.BreedPictureAdapter;
import com.kyros.technologies.dogbreeds.Interface.PojoInterface;
import com.kyros.technologies.dogbreeds.Pojo.BreedPictureResponse;
import com.kyros.technologies.dogbreeds.R;
import com.kyros.technologies.dogbreeds.ServiceHandler.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedPictureActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_STORAGE = 2;
    private BreedPictureAdapter adapter;
    private RecyclerView recycler_allpictures;
    private String breed=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_breed_picture);
        recycler_allpictures=(RecyclerView)findViewById(R.id.recycler_allpictures);
        try{
            Bundle bundle=getIntent().getExtras();
            breed=bundle.getString("breed");
        }catch (Exception e){
            e.printStackTrace();
        }
        if (ContextCompat.checkSelfPermission(BreedPictureActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(BreedPictureActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(BreedPictureActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        PojoInterface apiService= ApiClient.getRetrofit().create(PojoInterface.class);
        Call<BreedPictureResponse>call=apiService.getBreedPictures(breed);
        call.enqueue(new Callback<BreedPictureResponse>() {
            @Override
            public void onResponse(Call<BreedPictureResponse> call, Response<BreedPictureResponse> response) {
                BreedPictureResponse res=response.body();
                List<String> picturelist=res.getMessage();

                adapter=new BreedPictureAdapter(BreedPictureActivity.this,picturelist);
                RecyclerView.LayoutManager layoutManagersecond=new LinearLayoutManager(getApplicationContext());
                recycler_allpictures.setLayoutManager(layoutManagersecond);
                recycler_allpictures.setItemAnimator(new DefaultItemAnimator());

                recycler_allpictures.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BreedPictureResponse> call, Throwable t) {

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
