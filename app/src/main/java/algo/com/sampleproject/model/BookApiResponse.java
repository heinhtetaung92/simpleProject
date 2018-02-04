
package algo.com.sampleproject.model;


import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class BookApiResponse {

    public BookApiResponse() {
    }

//    public BookApiResponse(String mCopyright, String mLastModified, Long mNumResults, List<Result> mResults, String mStatus) {
//        this.mCopyright = mCopyright;
//        this.mLastModified = mLastModified;
//        this.mNumResults = mNumResults;
//        this.mResults = mResults;
//        this.mStatus = mStatus;
//    }

    @SerializedName("copyright")
    private String mCopyright;
    @SerializedName("last_modified")
    private String mLastModified;
    @SerializedName("num_results")
    private Long mNumResults;
    @SerializedName("results")
    private List<Result> mResults;
    @SerializedName("status")
    private String mStatus;

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public String getLastModified() {
        return mLastModified;
    }

    public void setLastModified(String lastModified) {
        mLastModified = lastModified;
    }

    public Long getNumResults() {
        return mNumResults;
    }

    public void setNumResults(Long numResults) {
        mNumResults = numResults;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
