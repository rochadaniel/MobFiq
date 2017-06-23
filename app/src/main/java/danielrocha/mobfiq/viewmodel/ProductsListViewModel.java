package danielrocha.mobfiq.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.helper.ConnectivityHelper;
import danielrocha.mobfiq.model.ItemResult;
import danielrocha.mobfiq.model.ParamsAPI;
import danielrocha.mobfiq.model.Product;
import danielrocha.mobfiq.service.ProductsAPI;
import danielrocha.mobfiq.service.ServiceGenerator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ProductsListViewModel extends Observable {
    public ObservableInt isLoading, hasList, hasError;
    public ObservableField<String> messageLabel;
    private List<Product> productList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Context context;
    private ItemResult itemResult;
    public int totalPages;
    private boolean firstTime = true;

    public ProductsListViewModel(Context context) {
        this.context = context;
        this.productList = new ArrayList<>();

        initViews();
    }

    public void initViews() {
        isLoading = new ObservableInt(View.VISIBLE);
        hasList = new ObservableInt(View.GONE);
        hasError = new ObservableInt(View.GONE);
        messageLabel = new ObservableField<>("");
    }

    public void showError(String errorMessage) {
        isLoading.set(View.GONE);
        hasError.set(View.VISIBLE);
        hasList.set(View.GONE);
        messageLabel.set(errorMessage);
    }

    private void changeDataSet(List<Product> productList) {
        this.productList = productList;
        setChanged();
        notifyObservers();
    }

    public List<Product> getProductList() {
        return productList;
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

    public void getItens(ParamsAPI paramsAPI) {
        try {
            if(ConnectivityHelper.isConnected(context)) {

                if(firstTime) {
                    isLoading.set(View.VISIBLE);
                    hasError.set(View.GONE);
                    hasList.set(View.GONE);
                }

                ProductsAPI productsAPI = ServiceGenerator.createService(ProductsAPI.class);

                Disposable disposable = productsAPI.products(paramsAPI)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( itemResult -> {
                                    if (itemResult != null &&
                                            itemResult.getProducts() != null &&
                                            itemResult.getProducts().size() > 0) {
                                        this.itemResult = itemResult;
                                        totalPages = itemResult.getTotal();

                                        if(!firstTime) {
                                            //Removendo loading item
                                            productList.remove(productList.size() - 1);
                                        } else {
                                           firstTime = false;
                                        }

                                        productList.addAll(itemResult.getProducts());
                                        changeDataSet(productList);
                                        hasList.set(View.VISIBLE);
                                        isLoading.set(View.GONE);
                                    } else {
                                        if(firstTime) {
                                            isLoading.set(View.GONE);
                                            hasError.set(View.VISIBLE);
                                            showError(context.getString(R.string.without_product));
                                        }
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

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
}
