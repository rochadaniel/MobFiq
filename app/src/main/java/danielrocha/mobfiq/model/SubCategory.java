package danielrocha.mobfiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielrocha on 22/06/17.
 */

public class SubCategory {
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Image")
    @Expose
    private Object image;
    @SerializedName("Redirect")
    @Expose
    private Redirect_ redirect;
    @SerializedName("SubCategories")
    @Expose
    private Object subCategories;
    @SerializedName("Highlight")
    @Expose
    private boolean highlight;
    @SerializedName("Icon")
    @Expose
    private Object icon;
    @SerializedName("CategoryListOrder")
    @Expose
    private int categoryListOrder;
    @SerializedName("CategoryTreeOrder")
    @Expose
    private int categoryTreeOrder;
    @SerializedName("LinkId")
    @Expose
    private int linkId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Redirect_ getRedirect() {
        return redirect;
    }

    public void setRedirect(Redirect_ redirect) {
        this.redirect = redirect;
    }

    public Object getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Object subCategories) {
        this.subCategories = subCategories;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public int getCategoryListOrder() {
        return categoryListOrder;
    }

    public void setCategoryListOrder(int categoryListOrder) {
        this.categoryListOrder = categoryListOrder;
    }

    public int getCategoryTreeOrder() {
        return categoryTreeOrder;
    }

    public void setCategoryTreeOrder(int categoryTreeOrder) {
        this.categoryTreeOrder = categoryTreeOrder;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }
}
