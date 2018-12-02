package by.boiko.erizo.testdes.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employee")
    Flowable<List<Employee>> getAll();

    @Insert
    void insert(List<Employee> employee);

    @Query("SELECT * FROM employee WHERE type LIKE '%' || :search || '%' OR title LIKE '%' || :search || '%'")
    Flowable<List<Employee>> getAllWithNameLike(String search);

    @Query( "SELECT * FROM employee ORDER BY amount ASC")
    Flowable<List<Employee>> ascendingSorting();

    @Query( "SELECT * FROM employee ORDER BY amount DESC")
    Flowable<List<Employee>> descendingSorting();
}

