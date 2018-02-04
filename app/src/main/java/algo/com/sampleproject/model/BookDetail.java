
package algo.com.sampleproject.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
@Entity
public class BookDetail {

    public BookDetail() {
    }

//    public BookDetail(String mAgeGroup, String mAuthor, String mContributor, String mContributorNote, String mDescription, Long mPrice, String mPrimaryIsbn10, String mPrimaryIsbn13, String mPublisher, String mTitle) {
//        this.mAgeGroup = mAgeGroup;
//        this.mAuthor = mAuthor;
//        this.mContributor = mContributor;
//        this.mContributorNote = mContributorNote;
//        this.mDescription = mDescription;
//        this.mPrice = mPrice;
//        this.mPrimaryIsbn10 = mPrimaryIsbn10;
//        this.mPrimaryIsbn13 = mPrimaryIsbn13;
//        this.mPublisher = mPublisher;
//        this.mTitle = mTitle;
//    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="_id" )
    public int id;
    @ColumnInfo(name = "age_group")
    @SerializedName("age_group")
    private String mAgeGroup;
    @ColumnInfo(name = "author")
    @SerializedName("author")
    private String mAuthor;
    @ColumnInfo(name = "contributor")
    @SerializedName("contributor")
    private String mContributor;
    @ColumnInfo(name = "contributor_note")
    @SerializedName("contributor_note")
    private String mContributorNote;
    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String mDescription;
    @ColumnInfo(name = "price")
    @SerializedName("price")
    private Long mPrice;
    @ColumnInfo(name = "primary_isbn10")
    @SerializedName("primary_isbn10")
    private String mPrimaryIsbn10;
    @ColumnInfo(name = "primary_isbn13")
    @SerializedName("primary_isbn13")
    private String mPrimaryIsbn13;
    @ColumnInfo(name = "publisher")
    @SerializedName("publisher")
    private String mPublisher;
    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String mTitle;

    public String getAgeGroup() {
        return mAgeGroup;
    }

    public void setAgeGroup(String ageGroup) {
        mAgeGroup = ageGroup;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getContributor() {
        return mContributor;
    }

    public void setContributor(String contributor) {
        mContributor = contributor;
    }

    public String getContributorNote() {
        return mContributorNote;
    }

    public void setContributorNote(String contributorNote) {
        mContributorNote = contributorNote;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getPrice() {
        return mPrice;
    }

    public void setPrice(Long price) {
        mPrice = price;
    }

    public String getPrimaryIsbn10() {
        return mPrimaryIsbn10;
    }

    public void setPrimaryIsbn10(String primaryIsbn10) {
        mPrimaryIsbn10 = primaryIsbn10;
    }

    public String getPrimaryIsbn13() {
        return mPrimaryIsbn13;
    }

    public void setPrimaryIsbn13(String primaryIsbn13) {
        mPrimaryIsbn13 = primaryIsbn13;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
