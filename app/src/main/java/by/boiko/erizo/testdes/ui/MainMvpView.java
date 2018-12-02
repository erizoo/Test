package by.boiko.erizo.testdes.ui;

import java.util.List;

import by.boiko.erizo.testdes.db.Employee;
import by.boiko.erizo.testdes.ui.base.MvpView;

public interface MainMvpView  extends MvpView {

    void updateData(List<Employee> employees);

    void onSavedData();

    void onSearchedData(List<Employee> employees);

    void onAscendingSortingData(List<Employee> employees);

    void onDescendingSortingData(List<Employee> employees);
}
