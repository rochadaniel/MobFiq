package danielrocha.mobfiq.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.model.Category;
import danielrocha.mobfiq.model.SubCategory;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by danielrocha on 22/06/17.
 */

public class MainViewModel extends Observable {
    public ObservableField<String> categoryLabel, messageLabel;
    public ObservableInt isLoading, hasList, hasError;
    private List<Category> categoryList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Context context;

    public MainViewModel(Context context) {
        this.context = context;
        this.categoryList = new ArrayList<>();

        initViews();
    }

    public void initViews() {
        isLoading = new ObservableInt(View.VISIBLE);
        hasList = new ObservableInt(View.GONE);
        hasError = new ObservableInt(View.GONE);
        messageLabel = new ObservableField<>("");
        categoryLabel = new ObservableField<>(context.getString(R.string.all_categories));
    }

    public void showError(String errorMessage) {
        isLoading.set(View.GONE);
        hasError.set(View.VISIBLE);
        hasList.set(View.GONE);
        messageLabel.set(errorMessage);
    }

    private void changeDataSet(List<Category> categoryList) {
        this.categoryList = categoryList;
        setChanged();
        notifyObservers();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }

    public void getCategories() {
        List<Category> categoryList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Category category = new Category();
            category.setName("Categoria: " + i);
            List<SubCategory> subCategoryList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                SubCategory subCategory = new SubCategory();
                subCategory.setName("SubCategoria: " + j);
                subCategoryList.add(subCategory);
                category.setSubCategories(subCategoryList);
            }
            categoryList.add(category);
        }

        changeDataSet(categoryList);
        hasList.set(View.VISIBLE);
        isLoading.set(View.GONE);
    }
}
