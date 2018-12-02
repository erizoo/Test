package by.boiko.erizo.testdes.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();

}

