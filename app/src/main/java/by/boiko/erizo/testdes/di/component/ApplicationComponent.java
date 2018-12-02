package by.boiko.erizo.testdes.di.component;

import android.content.Context;


import javax.inject.Singleton;

import by.boiko.erizo.testdes.TestDes;
import by.boiko.erizo.testdes.di.ApplicationContext;
import by.boiko.erizo.testdes.di.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(TestDes application);

    @ApplicationContext
    Context context();


}

