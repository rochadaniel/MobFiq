package danielrocha.mobfiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielrocha on 22/06/17.
 */

public class SearchCriteria {
    @SerializedName("Query")
    @Expose
    private Object query;
    @SerializedName("OrderBy")
    @Expose
    private int orderBy;
    @SerializedName("Size")
    @Expose
    private Object size;
    @SerializedName("Offset")
    @Expose
    private Object offset;
    @SerializedName("Filter")
    @Expose
    private Object filter;
    @SerializedName("ApiQuery")
    @Expose
    private String apiQuery;
    @SerializedName("ProductId")
    @Expose
    private Object productId;
    @SerializedName("Hotsite")
    @Expose
    private Object hotsite;
    @SerializedName("RealProductId")
    @Expose
    private Object realProductId;
    @SerializedName("EAN")
    @Expose
    private Object eAN;
    @SerializedName("RealProductIdGroup")
    @Expose
    private Object realProductIdGroup;
    @SerializedName("FacetItems")
    @Expose
    private Object facetItems;
    @SerializedName("SearchApi")
    @Expose
    private Object searchApi;

    public Object getQuery() {
        return query;
    }

    public void setQuery(Object query) {
        this.query = query;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public Object getSize() {
        return size;
    }

    public void setSize(Object size) {
        this.size = size;
    }

    public Object getOffset() {
        return offset;
    }

    public void setOffset(Object offset) {
        this.offset = offset;
    }

    public Object getFilter() {
        return filter;
    }

    public void setFilter(Object filter) {
        this.filter = filter;
    }

    public String getApiQuery() {
        return apiQuery;
    }

    public void setApiQuery(String apiQuery) {
        this.apiQuery = apiQuery;
    }

    public Object getProductId() {
        return productId;
    }

    public void setProductId(Object productId) {
        this.productId = productId;
    }

    public Object getHotsite() {
        return hotsite;
    }

    public void setHotsite(Object hotsite) {
        this.hotsite = hotsite;
    }

    public Object getRealProductId() {
        return realProductId;
    }

    public void setRealProductId(Object realProductId) {
        this.realProductId = realProductId;
    }

    public Object getEAN() {
        return eAN;
    }

    public void setEAN(Object eAN) {
        this.eAN = eAN;
    }

    public Object getRealProductIdGroup() {
        return realProductIdGroup;
    }

    public void setRealProductIdGroup(Object realProductIdGroup) {
        this.realProductIdGroup = realProductIdGroup;
    }

    public Object getFacetItems() {
        return facetItems;
    }

    public void setFacetItems(Object facetItems) {
        this.facetItems = facetItems;
    }

    public Object getSearchApi() {
        return searchApi;
    }

    public void setSearchApi(Object searchApi) {
        this.searchApi = searchApi;
    }
}
