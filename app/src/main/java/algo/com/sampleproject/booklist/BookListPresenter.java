package algo.com.sampleproject.booklist;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import algo.com.sampleproject.database.AppDatabase;
import algo.com.sampleproject.database.ResultDao;
import algo.com.sampleproject.model.BookApiResponse;
import algo.com.sampleproject.model.Result;
import algo.com.sampleproject.restapi.RestService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by heinhtetaung on 3/2/18.
 */

public class BookListPresenter implements BookListContract.Presenter {

    private BookListContract.View mView;
    private RestService mRestService;
    private Context mContext;

    List<Result> mResultList;

    @Override
    public List<Result> getResultList() {
        return mResultList;
    }

    @Override
    public Result getResultByPosition(int pos) {
        return mResultList.get(pos);
    }

    @Override
    public void loadMoreItems() {
        Observable<BookApiResponse> apiRequest = mRestService.getIPService().getBookList("e-book-fiction");
        handleApiResponse(apiRequest);
    }

    @Override
    public void onRefresh() {
        clearData("Refresh");
    }


    public void setResultList(List<Result> mResultList) {
        this.mResultList = mResultList;
    }

    public BookListPresenter(BookListContract.View view) {
        this.mView = view;
    }

    @Override
    public void init(Context context) {
        mRestService = new RestService();
        mContext = context;
        mResultList = new ArrayList<>();

        showLoading();
        loadLocalDataAndShow();
    }

    @Override
    public void loadData() {

        Observable<BookApiResponse> apiRequest = mRestService.getIPService().getBookList("e-book-fiction");
        handleApiResponse(apiRequest);
    }

    public boolean isListEmpty(){
        return mResultList.isEmpty();
    }

    private void showLoading(){
        if(isListEmpty()){
            mView.showLoading();
        }else{
            mView.showPaginationLoading();
        }
    }

    private void dismissLoading(){
        if(isListEmpty()){
            mView.dismissLoading();
        }else{
            mView.dismissPaginationLoading();
        }

        mView.dismissRefreshLayout();
    }

    private void showErrorMessage(String msg){
        if(isListEmpty()){
            mView.showErrorMsg(msg);
        }else{
            mView.showToastMessage(msg);
        }
    }

    public void handleApiResponse(Observable<BookApiResponse> apiRequest){

        showLoading();

        apiRequest
        .map(new Function<BookApiResponse, Boolean>() {
            @Override
            public Boolean apply(BookApiResponse bookApiResponse) throws Exception {
                try {
                    Log.i(BookListPresenter.this.getClass().getName(), "Result size " + String.valueOf(bookApiResponse.getResults().size()));

                    ResultDao resultDao = AppDatabase.getDatabase(mContext).getResultDao();
//                    resultDao.nukeTable();
                    resultDao.insertResults(bookApiResponse.getResults());
                    mResultList.addAll(bookApiResponse.getResults());
                }catch (Exception ex){
                    return false;
                }

                return true;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean isSuccess) {

                        Log.i(BookListPresenter.this.getClass().getName(), String.valueOf(isSuccess));
                        if(isSuccess){
                            mView.showData();
                        }else{
                            onError(new Exception("Database Insertion Failed"));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i("BookList", "error");
                        //show error layout
                        showErrorMessage("Failed to load data");
                    }

                    @Override
                    public void onComplete() {
                        // TODO: 3/2/18 save data done
                        dismissLoading();
                    }
                });
    }

    public void clearData(String process){
        Observable.just(process)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        mResultList.clear();
                        AppDatabase.getDatabase(mContext).getResultDao().nukeTable();
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        loadData();
                    }
                });
    }

    @Override
    public void loadLocalDataAndShow() {

        Observable.just(AppDatabase.getDatabase(mContext))
                .map(new Function<AppDatabase, List<Result>>() {
                    @Override
                    public List<Result> apply(AppDatabase appDatabase) throws Exception {
                        return appDatabase.getResultDao().loadResults();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Result>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Result> results) {
                        Log.i(BookListPresenter.this.getClass().getName(), "Offline Result size " + String.valueOf(results.size()));
                        mResultList.addAll(results);
                        mView.showData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showErrorMessage("Failed to load data");
                    }

                    @Override
                    public void onComplete() {
                        dismissLoading();

                        if(isListEmpty()){
                            loadData();
                        }else{
                            mView.showErrorMsg("Empty");
                        }
                    }
                });
    }
}
