package algo.com.sampleproject.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import algo.com.sampleproject.model.BookApiResponse;
import algo.com.sampleproject.model.Result;

@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

  private static AppDatabase INSTANCE;

  public static AppDatabase getDatabase(Context context) {
    if (INSTANCE == null) {
      INSTANCE =
          Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "book_db")
              .build();
    }
    return INSTANCE;
  }

  public AppDatabase() {
  }

  public static void destroyInstance() {
    INSTANCE = null;
  }

  public abstract ResultDao getResultDao();
}