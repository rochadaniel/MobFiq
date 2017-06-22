package danielrocha.mobfiq.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import danielrocha.mobfiq.model.ItemResult;
import danielrocha.mobfiq.model.ParamsAPI;
import danielrocha.mobfiq.model.Product;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ProductsListViewModel extends Observable {
    public ObservableInt isLoading, hasList, hasError;
    public ObservableField<String> messageLabel;
    private List<Product> productList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Context context;

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
        Toast.makeText(context, "Teste: " + paramsAPI.getSize(), Toast.LENGTH_SHORT).show();
        ItemResult itemResult = new ItemResult();
        List<Product> productList = new ArrayList<>();

        Product p1 = new Product();
        p1.setName("p1");
        productList.add(p1);

        Product p2 = new Product();
        p2.setName("p2");
        productList.add(p2);

        itemResult.setProducts(productList);

        changeDataSet(productList);
        hasList.set(View.VISIBLE);
        isLoading.set(View.GONE);
    }
}
