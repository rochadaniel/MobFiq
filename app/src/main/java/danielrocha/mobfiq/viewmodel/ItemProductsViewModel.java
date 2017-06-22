package danielrocha.mobfiq.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import danielrocha.mobfiq.model.Product;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ItemProductsViewModel extends BaseObservable {

    private Product product;
    private Context context;

    public ItemProductsViewModel(Product product, Context context) {
        this.product = product;
        this.context = context;
    }

    public String getName() {
        return product.getName();
    }

    public void setProduct(Product product) {
        this.product = product;
        notifyChange();
    }
}
