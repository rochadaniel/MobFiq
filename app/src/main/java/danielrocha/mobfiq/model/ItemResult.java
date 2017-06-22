package danielrocha.mobfiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ItemResult implements Serializable {
    @SerializedName("Size")
    @Expose
    private int size;
    @SerializedName("Offset")
    @Expose
    private int offset;
    @SerializedName("Total")
    @Expose
    private int total;
    @SerializedName("Delay")
    @Expose
    private double delay;
    @SerializedName("Products")
    @Expose
    private List<Product> products = null;
    @SerializedName("ApiQuery")
    @Expose
    private String apiQuery;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getApiQuery() {
        return apiQuery;
    }

    public void setApiQuery(String apiQuery) {
        this.apiQuery = apiQuery;
    }
}
