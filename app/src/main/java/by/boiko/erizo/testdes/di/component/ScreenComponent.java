package by.boiko.erizo.testdes.di.component;

import by.boiko.erizo.testdes.ui.MainActivity;
import by.boiko.erizo.testdes.di.PerScreen;
import by.boiko.erizo.testdes.di.module.ScreenModule;
import dagger.Component;

@PerScreen
@Component(modules = ScreenModule.class, dependencies = ApplicationComponent.class)
public interface ScreenComponent {

    void inject(MainActivity activity);

}
