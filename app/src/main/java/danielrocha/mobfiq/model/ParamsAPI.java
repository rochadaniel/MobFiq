package danielrocha.mobfiq.model;

import java.io.Serializable;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ParamsAPI implements Serializable {
    private String query = "";
    private int offSet = 0, size = 0;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
