package algo.com.sampleproject.booklist;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import algo.com.sampleproject.R;
import algo.com.sampleproject.database.AppDatabase;
import algo.com.sampleproject.model.Result;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by heinhtetaung on 4/2/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BookListActivityTest extends InstrumentationTestCase{

    private MockWebServer server;

    @Before
    public void setup() throws IOException {
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void readDatabase(){
        Context ctx = InstrumentationRegistry.getTargetContext();
        List<Result> results = AppDatabase.getDatabase(ctx).getResultDao().loadResults();
        Assert.assertNotNull(results);
    }

    @Test
    public void readAssets() throws Exception {
        Context ctx = InstrumentationRegistry.getTargetContext();
        InputStream is = ctx.getResources().getAssets().open("booklistresponse.json");
        String s = readTextStream(is);
        Assert.assertNotNull(s);
    }

    public static String readTextStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

    @Rule
    public ActivityTestRule<BookListActivity> mActivityRule =
            new ActivityTestRule<>(BookListActivity.class);

    @Test
    public void testProductIsShownInRecyclerView() throws Exception {

        Context ctx = InstrumentationRegistry.getTargetContext();
        InputStream is = ctx.getResources().getAssets().open("booklistresponse.json");
        String s = readTextStream(is);

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(s));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

}