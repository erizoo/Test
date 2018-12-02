package by.boiko.erizo.testdes.ui;

import by.boiko.erizo.testdes.ui.base.MvpPresenter;

public interface MainMvpPresenter <V extends MainMvpView> extends MvpPresenter<V> {

    void filter();
}
