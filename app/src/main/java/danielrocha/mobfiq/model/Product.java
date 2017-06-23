package danielrocha.mobfiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danielrocha on 22/06/17.
 */

public class Product implements Serializable {
    @SerializedName("Availability")
    @Expose
    private boolean availability;
    @SerializedName("Skus")
    @Expose
    private List<Sku> skus = null;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Brand")
    @Expose
    private String brand;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("Categories")
    @Expose
    private List<String> categories = null;
    @Expose
    private List<String> videos = null;
    @SerializedName("Images")
    @Expose
    private Object images;
    @SerializedName("RealId")
    @Expose
    private String realId;

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public String getRealId() {
        return realId;
    }

    public void setRealId(String realId) {
        this.realId = realId;
    }
}
