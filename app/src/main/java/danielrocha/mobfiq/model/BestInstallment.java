package danielrocha.mobfiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielrocha on 22/06/17.
 */

public class BestInstallment {
    @SerializedName("Count")
    @Expose
    private int count;
    @SerializedName("Value")
    @Expose
    private double value;
    @SerializedName("Total")
    @Expose
    private double total;
    @SerializedName("Rate")
    @Expose
    private int rate;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
