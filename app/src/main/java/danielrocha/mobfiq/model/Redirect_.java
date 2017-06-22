package danielrocha.mobfiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by danielrocha on 22/06/17.
 */

public class Redirect_ implements Serializable {
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("SearchCriteria")
    @Expose
    private SearchCriteria_ searchCriteria;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Type")
    @Expose
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SearchCriteria_ getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria_ searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
