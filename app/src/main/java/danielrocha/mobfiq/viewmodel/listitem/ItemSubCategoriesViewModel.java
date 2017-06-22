package danielrocha.mobfiq.viewmodel.listitem;

import android.content.Context;
import android.databinding.BaseObservable;

import danielrocha.mobfiq.model.SubCategory;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ItemSubCategoriesViewModel extends BaseObservable {
    private SubCategory subCategory;
    private Context context;

    public ItemSubCategoriesViewModel(SubCategory subCategory, Context context) {
        this.subCategory = subCategory;
        this.context = context;
    }

    public String getName() {
        return subCategory.getName();
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        notifyChange();
    }
}
