package danielrocha.mobfiq.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import danielrocha.mobfiq.model.Category;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ItemCategoriesViewModel extends BaseObservable {
    private Category category;
    private Context context;

    public ItemCategoriesViewModel(Category category, Context context) {
        this.category = category;
        this.context = context;
    }

    public String getName() {
        return category.getName();
    }

    public void setCategory(Category category) {
        this.category = category;
        notifyChange();
    }
}
