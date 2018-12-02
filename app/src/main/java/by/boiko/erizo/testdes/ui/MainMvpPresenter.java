package by.boiko.erizo.testdes.ui;

import by.boiko.erizo.testdes.db.Employee;
import by.boiko.erizo.testdes.ui.base.MvpPresenter;

public interface MainMvpPresenter <V extends MainMvpView> extends MvpPresenter<V> {

    void getData();

    void saveData();

    void search(String newText);

    void ascendingSorting();

    void descendingSorting();
}
