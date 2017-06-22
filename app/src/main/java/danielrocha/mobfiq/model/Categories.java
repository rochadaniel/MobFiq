package danielrocha.mobfiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by danielrocha on 22/06/17.
 */

public class Categories implements Serializable {
    @SerializedName("Categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("Id")
    @Expose
    private int id;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
