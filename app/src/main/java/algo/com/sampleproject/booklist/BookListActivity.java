package algo.com.sampleproject.booklist;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import algo.com.sampleproject.R;
import algo.com.sampleproject.database.AppDatabase;
import algo.com.sampleproject.model.Result;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListActivity extends AppCompatActivity implements BookListContract.View {

    private final int PAGE_SIZE = 15;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    BookListAdapter mAdapter;
    BookListPresenter mPresenter;

    //to track pagination
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        ButterKnife.bind(this);

        init();

    }

    private void init() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setOnScrollListener(recyclerViewOnScrollListener);

        mPresenter = new BookListPresenter(this);
        mPresenter.init(this);

        mAdapter = new BookListAdapter(mPresenter);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefresh();
            }
        });
    }

    RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    loadMoreItems();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDatabase.destroyInstance();
    }

    private void loadMoreItems() {
        mPresenter.loadMoreItems();
    }


    @Override
    public void showData() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String msg) {
        // TODO: 3/2/18 show error message
    }

    @Override
    public void showToastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        isLoading = true;
    }

    @Override
    public void showPaginationLoading() {
        isLoading = true;
    }

    @Override
    public void dismissLoading() {
        isLoading = false;
    }

    @Override
    public void dismissPaginationLoading() {
        isLoading = false;
    }

    @Override
    public void dismissRefreshLayout() {

        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
