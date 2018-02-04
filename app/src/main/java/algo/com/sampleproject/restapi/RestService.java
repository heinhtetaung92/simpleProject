package algo.com.sampleproject.restapi;

import com.google.gson.JsonObject;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by heinhtetaung on 3/2/18.
 */

public class RestService {

    Retrofit retrofit;

    public RestService(){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(IPService.END)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public IPService getIPService(){
        return retrofit.create(IPService.class);
    }


    static class LoggingInterceptor implements Interceptor {
        @Override public Response intercept(Chain chain) throws IOException {
            Request request= chain.request();
            HttpUrl originalHttpUrl = request.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api-key", IPService.API_KEY)
                    .build();

            // Request customization: add request headers
            Request.Builder requestBuilder = request.newBuilder()
                    .url(url);

            request = requestBuilder.build();

            long t1 = System.nanoTime();
            System.out.println(
                    String.format("Sending request %s on %s%n%s", request.url(), chain.connection(),
                            request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            System.out.println(
                    String.format("Received response for %s in %.1fms%n%s", response.request().url(),
                            (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

}
