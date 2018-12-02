package by.boiko.erizo.testdes.ui;

import android.database.Observable;

import javax.inject.Inject;

import by.boiko.erizo.testdes.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainMvpPresenterImpl<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    @Inject
    public MainMvpPresenterImpl(CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
    }

    @Override
    public void filter() {

    }
}
