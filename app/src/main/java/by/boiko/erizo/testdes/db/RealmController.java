package by.boiko.erizo.testdes.db;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmController {

    private Realm realm;

    public RealmController(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }

    public void addInfo(String title, int amount) {
        realm.beginTransaction();

        RealmModel realmObject = realm.createObject(RealmModel.class);
        int id = getNextKey();
        realmObject.setId(id);
        realmObject.setTitle(title);
        realmObject.setAmount(amount);

        realm.commitTransaction();
    }

    public RealmResults<RealmModel> getInfo() {
        return realm.where(RealmModel.class).findAll();
    }

    public RealmResults<RealmModel> ascendingSorting() {
        realm.beginTransaction();
        RealmResults<RealmModel> rows = realm.where(RealmModel.class).findAll();
        realm.commitTransaction();
        return rows.sort("amount",Sort.ASCENDING);
    }

    public RealmResults<RealmModel> descendingSorting() {
        realm.beginTransaction();
        RealmResults<RealmModel> rows = realm.where(RealmModel.class).findAll();
        realm.commitTransaction();
        return rows.sort("amount",Sort.DESCENDING);
    }


    public RealmResults<RealmModel> searchForTitle(String title) {
        realm.beginTransaction();
        RealmResults<RealmModel> rows = realm.where(RealmModel.class).contains("title", title).findAll();
        realm.commitTransaction();
        return rows;
    }

    public void deleteAll() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    private int getNextKey() {
        return realm.where(RealmModel.class).max("id").intValue() + 1;
    }
}
