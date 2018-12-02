package by.boiko.erizo.testdes.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.boiko.erizo.testdes.R;
import by.boiko.erizo.testdes.TestDes;
import by.boiko.erizo.testdes.db.AppDatabase;
import by.boiko.erizo.testdes.db.Employee;
import by.boiko.erizo.testdes.db.RealmController;
import by.boiko.erizo.testdes.db.RealmModel;
import by.boiko.erizo.testdes.ui.adapters.RealmAdapter;
import by.boiko.erizo.testdes.ui.base.BaseActivity;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends BaseActivity implements MainMvpView{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.search_view)
    SearchView searchView;

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    private RealmAdapter realmAdapter;
    private List<Employee> employees = new ArrayList<>();
    private boolean isSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getScreenComponent().inject(this);
        presenter.onAttach(this);

        realmAdapter = new RealmAdapter();

        SharedPreferences sharedPreferences = this.getPreferences(MODE_PRIVATE);
        isSaved = sharedPreferences.getBoolean("IS_SAVED", false);

        if (!isSaved){
            presenter.saveData();
        }
        presenter.getData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.search(newText);
                return true;
            }
        });
        searchView.onActionViewExpanded();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    private void setupAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(realmAdapter);

        realmAdapter.setItems(employees);
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.first_button)
    public void ascendingSorting(){
        presenter.ascendingSorting();
    }

    @OnClick(R.id.second_button)
    public void descendingSorting(){
        presenter.descendingSorting();
    }

    @Override
    public void updateData(List<Employee> employees) {
        this.employees.addAll(employees);
        setupAdapter();
    }

    @Override
    public void onSavedData() {
        SharedPreferences sharedPreferences = this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putBoolean("IS_SAVED", true);
        ed.apply();
    }

    @Override
    public void onSearchedData(List<Employee> employees) {
        realmAdapter.setItems(employees);
    }

    @Override
    public void onAscendingSortingData(List<Employee> employees) {
        realmAdapter.setItems(employees);
    }

    @Override
    public void onDescendingSortingData(List<Employee> employees) {
        realmAdapter.setItems(employees);
    }

}
