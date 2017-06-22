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

        Category c1 = new Category();
        c1.setName("c1");
        categoryList.add(c1);

        Category c2 = new Category();
        c2.setName("c2");
        categoryList.add(c2);

        changeDataSet(categoryList);
        hasList.set(View.VISIBLE);
        isLoading.set(View.GONE);
    }
}
