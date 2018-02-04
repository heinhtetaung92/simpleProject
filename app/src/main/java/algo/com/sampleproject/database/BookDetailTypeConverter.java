package algo.com.sampleproject.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import algo.com.sampleproject.model.BookDetail;

public class BookDetailTypeConverter {
    
    static Gson gson = new Gson();

    @TypeConverter
    public static List<BookDetail> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<BookDetail>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<BookDetail> someObjects) {
        return gson.toJson(someObjects);
    }
}