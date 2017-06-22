package danielrocha.mobfiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielrocha on 22/06/17.
 */

public class Seller {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Quantity")
    @Expose
    private int quantity;
    @SerializedName("Price")
    @Expose
    private double price;
    @SerializedName("ListPrice")
    @Expose
    private double listPrice;
    @SerializedName("BestInstallment")
    @Expose
    private BestInstallment bestInstallment;
    @SerializedName("Offer")
    @Expose
    private Object offer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public BestInstallment getBestInstallment() {
        return bestInstallment;
    }

    public void setBestInstallment(BestInstallment bestInstallment) {
        this.bestInstallment = bestInstallment;
    }

    public Object getOffer() {
        return offer;
    }

    public void setOffer(Object offer) {
        this.offer = offer;
    }
}
