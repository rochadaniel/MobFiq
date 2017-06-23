package danielrocha.mobfiq.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Observer;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.databinding.ActivitySearchResultBinding;
import danielrocha.mobfiq.model.ParamsAPI;
import danielrocha.mobfiq.model.SubCategory;
import danielrocha.mobfiq.view.fragment.ProductsListFragment;
import danielrocha.mobfiq.viewmodel.SearchResultViewModel;

public class SearchResultActivity extends AppCompatActivity {

    private ActivitySearchResultBinding activitySearchResultBinding;
    private SearchResultViewModel searchResultViewModel;
    public static final String SEARCH_TITLE_EXTRA = "SEARCH_TITLE_EXTRA";
    private String title = "";
    private ParamsAPI paramsAPI = new ParamsAPI();
    private ProductsListFragment productsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        if(savedInstanceState == null) {
            Intent intent = getIntent();

            if (intent.getSerializableExtra(ProductsListFragment.API_QUERY_EXTRA) != null)
                paramsAPI = (ParamsAPI) getIntent().getSerializableExtra(ProductsListFragment.API_QUERY_EXTRA);

            if(intent.getStringExtra(SEARCH_TITLE_EXTRA) != null)
                title = intent.getStringExtra(SEARCH_TITLE_EXTRA);
        } else {
            paramsAPI = (ParamsAPI) savedInstanceState.getSerializable(ProductsListFragment.API_QUERY_EXTRA);
            title = savedInstanceState.getString(SEARCH_TITLE_EXTRA);
        }
        initToolbar();
        setupProductFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(SEARCH_TITLE_EXTRA, title);
        outState.putSerializable(ProductsListFragment.API_QUERY_EXTRA, paramsAPI);
        super.onSaveInstanceState(outState);
    }

    private void initDataBinding() {
        activitySearchResultBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_result);
        searchResultViewModel = new SearchResultViewModel(SearchResultActivity.this);
        activitySearchResultBinding.setSearchResultViewModel(searchResultViewModel);
    }

    private void initToolbar() {
        activitySearchResultBinding.toolbar.setTitle(title);
        setSupportActionBar(activitySearchResultBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activitySearchResultBinding.toolbar.setNavigationOnClickListener( v -> {
            onBackPressed();
        });
    }

    private void setupProductFragment() {
        productsListFragment = ProductsListFragment.newInstance(paramsAPI);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, productsListFragment);
        ft.commit();
    }

}
