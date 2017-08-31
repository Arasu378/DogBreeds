package com.kyros.technologies.dogbreeds.ServiceHandler;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by kyros on 23-08-2017.
 */

public class ServiceHandler extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
