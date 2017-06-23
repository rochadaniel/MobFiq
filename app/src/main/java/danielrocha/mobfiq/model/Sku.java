package danielrocha.mobfiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danielrocha on 22/06/17.
 */

public class Sku implements Serializable {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Order")
    @Expose
    private Object order;
    @SerializedName("Sellers")
    @Expose
    private List<Seller> sellers = null;
    @SerializedName("Images")
    @Expose
    private List<Image> images = null;
    @SerializedName("SkuName")
    @Expose
    private String skuName;
    @SerializedName("UnitMultiplier")
    @Expose
    private int unitMultiplier;
    @SerializedName("ComplementName")
    @Expose
    private String complementName;
    @SerializedName("MeasurementUnit")
    @Expose
    private String measurementUnit;
    @SerializedName("ReferenceId")
    @Expose
    private List<ReferenceId> referenceId = null;
    @SerializedName("EAN")
    @Expose
    private String eAN;

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

    public Object getOrder() {
        return order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public int getUnitMultiplier() {
        return unitMultiplier;
    }

    public void setUnitMultiplier(int unitMultiplier) {
        this.unitMultiplier = unitMultiplier;
    }

    public String getComplementName() {
        return complementName;
    }

    public void setComplementName(String complementName) {
        this.complementName = complementName;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public List<ReferenceId> getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(List<ReferenceId> referenceId) {
        this.referenceId = referenceId;
    }

    public String getEAN() {
        return eAN;
    }

    public void setEAN(String eAN) {
        this.eAN = eAN;
    }
}
