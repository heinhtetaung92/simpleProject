
package algo.com.sampleproject.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import algo.com.sampleproject.database.BookDetailTypeConverter;
import algo.com.sampleproject.database.ReviewTypeConverter;

@SuppressWarnings("unused")
@Entity
public class Result {

    public Result() {
    }

//    public Result(int id, String mAmazonProductUrl, Long mAsterisk, String mBestsellersDate, List<BookDetail> mBookDetails, Long mDagger, String mDisplayName, String mListName, String mPublishedDate, Long mRank, Long mRankLastWeek, List<Review> mReviews, Long mWeeksOnList) {
//        this.id = id;
//        this.mAmazonProductUrl = mAmazonProductUrl;
//        this.mAsterisk = mAsterisk;
//        this.mBestsellersDate = mBestsellersDate;
//        this.mBookDetails = mBookDetails;
//        this.mDagger = mDagger;
//        this.mDisplayName = mDisplayName;
//        this.mListName = mListName;
//        this.mPublishedDate = mPublishedDate;
//        this.mRank = mRank;
//        this.mRankLastWeek = mRankLastWeek;
//        this.mReviews = mReviews;
//        this.mWeeksOnList = mWeeksOnList;
//    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="_id" )
    public int id;
    @ColumnInfo(name="amazon_product_url")
    @SerializedName("amazon_product_url")
    private String mAmazonProductUrl;
    @ColumnInfo(name="asterisk")
    @SerializedName("asterisk")
    private Long mAsterisk;
    @ColumnInfo(name="bestsellers_date")
    @SerializedName("bestsellers_date")
    private String mBestsellersDate;
    @SerializedName("book_details")
    @TypeConverters(BookDetailTypeConverter.class)
    public List<BookDetail> mBookDetails;
    @ColumnInfo(name="dagger")
    @SerializedName("dagger")
    private Long mDagger;
    @ColumnInfo(name="display_name")
    @SerializedName("display_name")
    private String mDisplayName;
    @ColumnInfo(name="list_name")
    @SerializedName("list_name")
    private String mListName;
    @ColumnInfo(name="published_date")
    @SerializedName("published_date")
    private String mPublishedDate;
    @ColumnInfo(name="rank")
    @SerializedName("rank")
    private Long mRank;
    @ColumnInfo(name="rank_last_week")
    @SerializedName("rank_last_week")
    private Long mRankLastWeek;
    @SerializedName("reviews")
    @TypeConverters(ReviewTypeConverter.class)
    public List<Review> mReviews;
    @ColumnInfo(name="weeks_on_list")
    @SerializedName("weeks_on_list")
    private Long mWeeksOnList;

    public String getAmazonProductUrl() {
        return mAmazonProductUrl;
    }

    public void setAmazonProductUrl(String amazonProductUrl) {
        mAmazonProductUrl = amazonProductUrl;
    }

    public Long getAsterisk() {
        return mAsterisk;
    }

    public void setAsterisk(Long asterisk) {
        mAsterisk = asterisk;
    }

    public String getBestsellersDate() {
        return mBestsellersDate;
    }

    public void setBestsellersDate(String bestsellersDate) {
        mBestsellersDate = bestsellersDate;
    }

    public List<BookDetail> getBookDetails() {
        return mBookDetails;
    }

    public void setBookDetails(List<BookDetail> bookDetails) {
        mBookDetails = bookDetails;
    }

    public Long getDagger() {
        return mDagger;
    }

    public void setDagger(Long dagger) {
        mDagger = dagger;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public String getListName() {
        return mListName;
    }

    public void setListName(String listName) {
        mListName = listName;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        mPublishedDate = publishedDate;
    }

    public Long getRank() {
        return mRank;
    }

    public void setRank(Long rank) {
        mRank = rank;
    }

    public Long getRankLastWeek() {
        return mRankLastWeek;
    }

    public void setRankLastWeek(Long rankLastWeek) {
        mRankLastWeek = rankLastWeek;
    }

    public List<Review> getReviews() {
        return mReviews;
    }

    public void setReviews(List<Review> reviews) {
        mReviews = reviews;
    }

    public Long getWeeksOnList() {
        return mWeeksOnList;
    }

    public void setWeeksOnList(Long weeksOnList) {
        mWeeksOnList = weeksOnList;
    }

}
