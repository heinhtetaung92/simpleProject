package algo.com.sampleproject.booklist;

import android.content.Context;

import java.util.List;

import algo.com.sampleproject.model.Result;

/**
 * Created by heinhtetaung on 3/2/18.
 */

public class BookListContract {

    public interface Presenter{
        void init(Context context);
        void loadData();
        void loadLocalDataAndShow();
        List<Result> getResultList();
        Result getResultByPosition(int pos);
        void loadMoreItems();
        void onRefresh();
    }

    public interface View{

        void showData();
        void showErrorMsg(String msg);
        void showToastMessage(String msg);
        void showLoading();
        void showPaginationLoading();
        void dismissLoading();
        void dismissPaginationLoading();
        void dismissRefreshLayout();
    }

}
