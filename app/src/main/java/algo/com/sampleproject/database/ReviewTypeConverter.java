package algo.com.sampleproject.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import algo.com.sampleproject.model.Review;

/**
 * Created by heinhtetaung on 3/2/18.
 */

public class ReviewTypeConverter {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<Review> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Review>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Review> someObjects) {
        return gson.toJson(someObjects);
    }
}
