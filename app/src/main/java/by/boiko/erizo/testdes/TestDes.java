package by.boiko.erizo.testdes;

import android.app.Application;

import by.boiko.erizo.testdes.di.component.ApplicationComponent;
import by.boiko.erizo.testdes.di.component.DaggerApplicationComponent;
import by.boiko.erizo.testdes.di.module.ApplicationModule;

public class TestDes extends Application {


    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
