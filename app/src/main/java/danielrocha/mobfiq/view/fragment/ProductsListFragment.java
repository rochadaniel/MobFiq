package danielrocha.mobfiq.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Observable;
import java.util.Observer;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.adapter.ProductsAdapter;
import danielrocha.mobfiq.databinding.FragmentProductListBinding;
import danielrocha.mobfiq.model.ParamsAPI;
import danielrocha.mobfiq.viewmodel.ProductsListViewModel;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ProductsListFragment extends Fragment implements Observer {

    private FragmentProductListBinding fragmentProductListBinding;
    private ProductsListViewModel productsListViewModel;
    //private ParamsAPI paramsAPI;
    public static final String API_QUERY_EXTRA = "API_QUERY_EXTRA";

    public static ProductsListFragment newInstance(ParamsAPI paramsAPI) {
        ProductsListFragment productsListFragment = new ProductsListFragment();

        Bundle args = new Bundle();
        args.putSerializable(API_QUERY_EXTRA, paramsAPI);
        productsListFragment.setArguments(args);

        return productsListFragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if(savedInstanceState == null) {
//            Bundle args = getArguments();
//            paramsAPI = args == null ? new ParamsAPI() : (ParamsAPI)args.getSerializable(API_QUERY_EXTRA);
//        } else {
//            paramsAPI = (ParamsAPI)savedInstanceState.getSerializable(API_QUERY_EXTRA);
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initDataBinding(inflater, container);

        return fragmentProductListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState == null) {
            Bundle args = getArguments();
            fragmentProductListBinding.setParamsAPI(args == null ? new ParamsAPI() : (ParamsAPI)args.getSerializable(API_QUERY_EXTRA));
        } else {
            fragmentProductListBinding.setParamsAPI((ParamsAPI)savedInstanceState.getSerializable(API_QUERY_EXTRA));
        }

        setupList(fragmentProductListBinding.recyclerProducts);
        setupObserver(productsListViewModel);
        new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread( () -> productsListViewModel.getItens(fragmentProductListBinding.getParamsAPI()) );
        }).start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(API_QUERY_EXTRA, fragmentProductListBinding.getParamsAPI());
    }

    private void initDataBinding(LayoutInflater inflater, ViewGroup container) {
        fragmentProductListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false);
        productsListViewModel = new ProductsListViewModel(getActivity().getApplicationContext());
        fragmentProductListBinding.setProductViewModel(productsListViewModel);
    }

    private void setupList(RecyclerView recyclerProducts) {
        ProductsAdapter adapter = new ProductsAdapter();
        recyclerProducts.setAdapter(adapter);
        recyclerProducts.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        productsListViewModel.reset();
    }

    @Override public void update(Observable observable, Object data) {
        if (observable instanceof ProductsListViewModel) {
            ProductsAdapter productsAdapter = (ProductsAdapter) fragmentProductListBinding.recyclerProducts.getAdapter();
            ProductsListViewModel productsListViewModel = (ProductsListViewModel) observable;
            productsAdapter.setProductList(productsListViewModel.getProductList());
        }
    }
}
