package danielrocha.mobfiq.viewmodel;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import danielrocha.mobfiq.model.SubCategory;

/**
 * Created by danielrocha on 22/06/17.
 */

public class SubCategoriesViewModel extends Observable {
    private List<SubCategory> subCategoryList;
    private Context context;

    public SubCategoriesViewModel(Context context) {
        this.context = context;
        this.subCategoryList = new ArrayList<>();
    }

    private void changeDataSet(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
        setChanged();
        notifyObservers();
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

}
