package com.kyros.technologies.dogbreeds.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.kyros.technologies.dogbreeds.Fragments.BrowseBreedFragment;
import com.kyros.technologies.dogbreeds.Fragments.ByBreedFragment;
import com.kyros.technologies.dogbreeds.Fragments.BySubBreedFragment;
import com.kyros.technologies.dogbreeds.Fragments.HomeFragment;
import com.kyros.technologies.dogbreeds.R;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment frag=new HomeFragment();

                    FragmentTransaction fragmentTrans=
                            getSupportFragmentManager().beginTransaction();
                    fragmentTrans.replace(R.id.content,frag);
                    fragmentTrans.addToBackStack(null);
                    fragmentTrans.commit();
                    return true;
                case R.id.navigation_bybreed:
                    ByBreedFragment fragbybreed=new ByBreedFragment();

                    FragmentTransaction fragmentTrans1=
                            getSupportFragmentManager().beginTransaction();
                    fragmentTrans1.replace(R.id.content,fragbybreed);
                    fragmentTrans1.addToBackStack(null);
                    fragmentTrans1.commit();
                    return true;
                case R.id.navigation_bysubbreed:
                    BySubBreedFragment frag_subbreed=new BySubBreedFragment();

                    FragmentTransaction fragmentTrans2=
                            getSupportFragmentManager().beginTransaction();
                    fragmentTrans2.replace(R.id.content,frag_subbreed);
                    fragmentTrans2.addToBackStack(null);
                    fragmentTrans2.commit();
                    return true;
                case R.id.navigation_browse:
                    BrowseBreedFragment frag_browsebreed=new BrowseBreedFragment();

                    FragmentTransaction fragmentTrans3=
                            getSupportFragmentManager().beginTransaction();
                    fragmentTrans3.replace(R.id.content,frag_browsebreed);
                    fragmentTrans3.addToBackStack(null);
                    fragmentTrans3.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main2);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        HomeFragment frag=new HomeFragment();

        FragmentTransaction fragmentTrans=
                getSupportFragmentManager().beginTransaction();
        fragmentTrans.replace(R.id.content,frag);
        fragmentTrans.addToBackStack(null);
        fragmentTrans.commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
