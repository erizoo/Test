package by.boiko.erizo.testdes.ui;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.boiko.erizo.testdes.TestDes;
import by.boiko.erizo.testdes.db.AppDatabase;
import by.boiko.erizo.testdes.db.Employee;
import by.boiko.erizo.testdes.ui.base.BasePresenter;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainMvpPresenterImpl<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    @Inject
    public MainMvpPresenterImpl(CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getData() {
        AppDatabase db = TestDes.getInstance().getDatabase();
        db.employeeDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> getMvpView().updateData(employees));
    }

    @SuppressLint("CheckResult")
    @Override
    public void search(String newText) {
        AppDatabase db = TestDes.getInstance().getDatabase();
        db.employeeDao().getAllWithNameLike(newText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> getMvpView().onSearchedData(employees));
    }

    @SuppressLint("CheckResult")
    @Override
    public void ascendingSorting() {
        AppDatabase db = TestDes.getInstance().getDatabase();
        db.employeeDao().ascendingSorting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> getMvpView().onAscendingSortingData(employees));
    }

    @SuppressLint("CheckResult")
    @Override
    public void descendingSorting() {
        AppDatabase db = TestDes.getInstance().getDatabase();
        db.employeeDao().descendingSorting()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> getMvpView().onDescendingSortingData(employees));
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveData() {
        AppDatabase db = TestDes.getInstance().getDatabase();
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("помидор", "овощи","https://images.ua.prom.st/738383973_w857_h642_pomidor.jpg",1));
        employees.add(new Employee("огурец", "овощи","http://ogurcy-v-teplice.com/wp-content/uploads/2017/03/Ogurets-tsezar.jpeg",3));
        employees.add(new Employee("банан", "фрукты","https://vegstore.com.ua/media/catalog/product/cache/1/image/650x/040ec09b1e35df139433887a97daa66f/1/1/116_1.jpg",5));
        Completable.fromAction(() -> db.employeeDao()
                .insert(employees))
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSavedData);
    }
}
