package by.boiko.erizo.testdes.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import by.boiko.erizo.testdes.TestDes;
import by.boiko.erizo.testdes.di.component.DaggerScreenComponent;
import by.boiko.erizo.testdes.di.component.ScreenComponent;
import by.boiko.erizo.testdes.di.module.ScreenModule;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    @Inject
    CompositeDisposable compositeDisposable;

    private Unbinder unbinder;
    private ScreenComponent screenComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);

        screenComponent = DaggerScreenComponent.builder()
                .screenModule(new ScreenModule(this))
                .applicationComponent(((TestDes) getApplication()).getApplicationComponent())
                .build();
    }

    protected abstract int getContentView();

    public ScreenComponent getScreenComponent() {
        return screenComponent;
    }


    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        super.onDestroy();
    }

}

