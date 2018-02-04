package algo.com.sampleproject.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import algo.com.sampleproject.model.Result;
import io.reactivex.Flowable;

/**
 * Created by heinhtetaung on 3/2/18.
 */

@Dao
public interface ResultDao {

    @Query("SELECT * from result")
    List<Result> loadResults();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResults(List<Result> results);

    @Delete
    void deleteResults(Result... results);

    @Query("DELETE FROM result")
    void nukeTable();
}
