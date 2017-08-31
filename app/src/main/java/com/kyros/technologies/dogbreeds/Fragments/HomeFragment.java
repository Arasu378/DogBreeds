package com.kyros.technologies.dogbreeds.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kyros.technologies.dogbreeds.Adapters.AllBreedsAdapter;
import com.kyros.technologies.dogbreeds.Interface.PojoInterface;
import com.kyros.technologies.dogbreeds.Pojo.BreedAllListResponse;
import com.kyros.technologies.dogbreeds.Pojo.BreedListResponse;
import com.kyros.technologies.dogbreeds.R;
import com.kyros.technologies.dogbreeds.ServiceHandler.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kyros on 23-08-2017.
 */

public class HomeFragment extends Fragment {
    private TextView test_id;
    private ProgressDialog progressDialog;
    private RecyclerView recycler_allbreeds;
    private AllBreedsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        test_id=(TextView)view.findViewById(R.id.test_id);
        recycler_allbreeds=(RecyclerView)view.findViewById(R.id.recycler_allbreeds);

        return view;
    }

    @Override
    public void onResume() {
        showProgressDialog();
        PojoInterface apiService= ApiClient.getRetrofit().create(PojoInterface.class);
        Call<BreedListResponse>call=apiService.getBreedAllListresponse();
        call.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {
                dismissProgressDialog();
                Toast.makeText(getContext(),"Successfully connected!",Toast.LENGTH_SHORT).show();
                test_id.setText(response.toString());
                BreedListResponse breedAllListResponse= response.body();
            List<String> breedlist=   breedAllListResponse.getMessage();
                adapter=new AllBreedsAdapter(getContext(),breedlist);
                RecyclerView.LayoutManager layoutManagersecond=new LinearLayoutManager(getContext());
                recycler_allbreeds.setLayoutManager(layoutManagersecond);
                recycler_allbreeds.setItemAnimator(new DefaultItemAnimator());

                recycler_allbreeds.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(),"Failed!",Toast.LENGTH_SHORT).show();
                test_id.setText(t.toString());

            }
        });
    //  String[]  breed_list = getResources().getStringArray(R.array.breed_list);


        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dismissProgressDialog();
    }

    @Override
    public void onStop() {
        super.onStop();
        dismissProgressDialog();
    }

    private void showProgressDialog(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(getContext());
            progressDialog.setMessage(getString(R.string.please_wait));
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }
    private void dismissProgressDialog(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
