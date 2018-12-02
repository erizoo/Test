package by.boiko.erizo.testdes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.boiko.erizo.testdes.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getScreenComponent().inject(this);
        presenter.onAttach(this);

        realmAdapter = new RealmAdapter();

        new RealmController(this).deleteAll();
        new RealmController(this).addInfo("помидор", 2);
        new RealmController(this).addInfo("банан", 5);
        new RealmController(this).addInfo("огурец", 3);
        new RealmController(this).addInfo("авокадо", 1);

        setupAdapter();
        Realm realm = Realm.getDefaultInstance();

        presenter.filter();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RealmResults<RealmModel> results = new RealmController(getContext()).searchForTitle(query);
                List<RealmModel> realmModelList = realm.copyFromRealm(results);
                realmAdapter.setItems(realmModelList);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.equals("")){
                    RealmResults<RealmModel> results = new RealmController(getContext()).searchForTitle(newText);
                    List<RealmModel> realmModelList = realm.copyFromRealm(results);
                    realmAdapter.setItems(realmModelList);
                } else {
                    RealmResults<RealmModel> results = new RealmController(getContext()).getInfo();
                    List<RealmModel> realmModelList = realm.copyFromRealm(results);
                    realmAdapter.setItems(realmModelList);
                }
                return true;
            }
        });
        searchView.onActionViewExpanded();
    }

    private void setupAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Realm realm = Realm.getDefaultInstance();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(realmAdapter);
        RealmResults<RealmModel> results = new RealmController(this).getInfo();
        List<RealmModel> realmModelList = realm.copyFromRealm(results);
        realmAdapter.setItems(realmModelList);
        progressBar.setVisibility(View.GONE);
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

}
