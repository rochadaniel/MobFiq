package danielrocha.mobfiq.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.helper.ConnectivityHelper;
import danielrocha.mobfiq.model.Category;
import danielrocha.mobfiq.model.Redirect_;
import danielrocha.mobfiq.model.SearchCriteria_;
import danielrocha.mobfiq.model.SubCategory;
import danielrocha.mobfiq.service.CategoriesAPI;
import danielrocha.mobfiq.service.ServiceGenerator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        try {
            if(ConnectivityHelper.isConnected(context)) {

                isLoading.set(View.VISIBLE);
                hasError.set(View.GONE);
                hasList.set(View.GONE);

                CategoriesAPI categoriesAPI = ServiceGenerator.createService(CategoriesAPI.class);

                Disposable disposable = categoriesAPI.categories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( categories -> {
                                    if (categories != null &&
                                            categories.getCategories() != null &&
                                            categories.getCategories().size() > 0) {
                                        this.categoryList = categories.getCategories();
                                        changeDataSet(this.categoryList);
                                        hasList.set(View.VISIBLE);
                                        isLoading.set(View.GONE);
                                    } else {
                                        isLoading.set(View.GONE);
                                        hasError.set(View.VISIBLE);
                                        showError(context.getString(R.string.without_product));
                                    }

                                }, Throwable ->
                                        showError(Throwable.getLocalizedMessage())
                        );

                compositeDisposable.add(disposable);
            } else {
                showError(context.getString(R.string.without_network_connection));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            showError(ex.getLocalizedMessage());
        }
    }
}
