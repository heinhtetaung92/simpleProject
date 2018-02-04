
package algo.com.sampleproject.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
@Entity
public class Review {

    public Review() {
    }

//    public Review(String mArticleChapterLink, String mBookReviewLink, String mFirstChapterLink, String mSundayReviewLink) {
//        this.mArticleChapterLink = mArticleChapterLink;
//        this.mBookReviewLink = mBookReviewLink;
//        this.mFirstChapterLink = mFirstChapterLink;
//        this.mSundayReviewLink = mSundayReviewLink;
//    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="_id" )
    public int id;
    @ColumnInfo(name = "article_chapter_link")
    @SerializedName("article_chapter_link")
    private String mArticleChapterLink;
    @ColumnInfo(name = "book_review_link")
    @SerializedName("book_review_link")
    private String mBookReviewLink;
    @ColumnInfo(name = "first_chapter_link")
    @SerializedName("first_chapter_link")
    private String mFirstChapterLink;
    @ColumnInfo(name = "sunday_review_link")
    @SerializedName("sunday_review_link")
    private String mSundayReviewLink;

    public String getArticleChapterLink() {
        return mArticleChapterLink;
    }

    public void setArticleChapterLink(String articleChapterLink) {
        mArticleChapterLink = articleChapterLink;
    }

    public String getBookReviewLink() {
        return mBookReviewLink;
    }

    public void setBookReviewLink(String bookReviewLink) {
        mBookReviewLink = bookReviewLink;
    }

    public String getFirstChapterLink() {
        return mFirstChapterLink;
    }

    public void setFirstChapterLink(String firstChapterLink) {
        mFirstChapterLink = firstChapterLink;
    }

    public String getSundayReviewLink() {
        return mSundayReviewLink;
    }

    public void setSundayReviewLink(String sundayReviewLink) {
        mSundayReviewLink = sundayReviewLink;
    }

}
