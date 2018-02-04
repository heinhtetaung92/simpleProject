package algo.com.sampleproject.booklist;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import algo.com.sampleproject.R;
import algo.com.sampleproject.model.BookDetail;
import algo.com.sampleproject.model.Result;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heinhtetaung on 3/2/18.
 */

public class BookListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BookListContract.Presenter mPresenter;

    public BookListAdapter(){
    }

    public BookListAdapter(BookListContract.Presenter presenter){
        mPresenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_booklist_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {
        BookViewHolder holder = (BookViewHolder) h;
        holder.showResult(mPresenter.getResultByPosition(position));
    }

    @Override
    public int getItemCount() {
        return mPresenter.getResultList().size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title_tv)
        TextView titleView;

        @BindView(R.id.description_tv)
        TextView descriptionView;

        BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void showResult(Result bookResult){

            List<BookDetail> books = bookResult.getBookDetails();
            if(books.size() > 0){
                showBook(books.get(0));
            }

        }

        public void showBook(BookDetail book){
            showTitle(book.getTitle());
            showDescription(book.getDescription());
        }

        private void showDescription(String description) {
            if(!TextUtils.isEmpty(description)){
                descriptionView.setText(description);
            }else{
                descriptionView.setText("");
            }
        }

        private void showTitle(String title){
            if(!TextUtils.isEmpty(title)){
                titleView.setText(title);
            }else{
                titleView.setText("");
            }
        }

    }

}
