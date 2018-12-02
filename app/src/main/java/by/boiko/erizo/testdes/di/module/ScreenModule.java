package by.boiko.erizo.testdes.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;


import by.boiko.erizo.testdes.di.ActivityContext;
import by.boiko.erizo.testdes.di.PerScreen;
import by.boiko.erizo.testdes.ui.MainMvpPresenter;
import by.boiko.erizo.testdes.ui.MainMvpPresenterImpl;
import by.boiko.erizo.testdes.ui.MainMvpView;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ScreenModule {

    private final AppCompatActivity activity;

    public ScreenModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerScreen
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainMvpPresenterImpl<MainMvpView> presenter) {
        return presenter;
    }

}
