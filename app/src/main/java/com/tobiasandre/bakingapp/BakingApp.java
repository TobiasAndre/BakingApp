package com.tobiasandre.bakingapp;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Tobias Andre on 23/08/2017.
 */

public class BakingApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // This instantiates DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
        // add for verbose logging
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V); // set to verbose logging
    }
}
