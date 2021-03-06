package org.smartregister.configurableviews.sample.application;

import android.content.Intent;

import org.smartregister.Context;
import org.smartregister.CoreLibrary;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.configurableviews.sample.repository.SampleRepository;
import org.smartregister.configurableviews.sample.service.SampleService;
import org.smartregister.repository.Repository;
import org.smartregister.view.activity.DrishtiApplication;

import static org.smartregister.util.Log.logError;

/**
 * Created by ndegwamartin on 19/02/2018.
 */
public class SampleApplication extends DrishtiApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        context = Context.getInstance();

        context.updateApplicationContext(getApplicationContext());

        //Initialize Modules
        CoreLibrary.init(context);
        ConfigurableViewsLibrary.init(context);

        startSampleViewConfigService(getApplicationContext());

    }

    public void startSampleViewConfigService(android.content.Context context) {
        Intent intent = new Intent(context, SampleService.class);
        context.startService(intent);
    }

    public static synchronized SampleApplication getInstance() {
        return (SampleApplication) mInstance;
    }

    @Override
    public Repository getRepository() {
        try {
            if (repository == null) {
                repository = new SampleRepository(getInstance().getApplicationContext(), context);
            }
        } catch (UnsatisfiedLinkError e) {
            logError("Error on getRepository: " + e);

        }
        return repository;
    }


    @Override
    public void logoutCurrentUser() {

    }

}
