package algo.com.sampleproject.restapi;

import algo.com.sampleproject.model.BookApiResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by heinhtetaung on 3/2/18.
 */

public interface  IPService {

    public final String API_KEY = "b4d6f18ccd0b48a6a99606cc3f211020";
    String END = "https://api.nytimes.com";

    @GET("/svc/books/v3/lists.json")
    Observable<BookApiResponse> getBookList(@Query("list") String list);

    @GET("/svc/books/v3/lists.json")
    Observable<BookApiResponse> getBookList(@Query("list") String list, @Query("offset") int offset);
}
