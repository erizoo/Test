package by.boiko.erizo.testdes;

import android.app.Application;
import android.arch.persistence.room.Room;

import by.boiko.erizo.testdes.db.AppDatabase;
import by.boiko.erizo.testdes.di.component.ApplicationComponent;
import by.boiko.erizo.testdes.di.component.DaggerApplicationComponent;
import by.boiko.erizo.testdes.di.module.ApplicationModule;

public class TestDes extends Application {

    private ApplicationComponent applicationComponent;
    private AppDatabase database;
    public static TestDes instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
        applicationComponent.inject(this);

    }

    public static TestDes getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
