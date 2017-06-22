package danielrocha.mobfiq.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.adapter.CategoriesAdapter;
import danielrocha.mobfiq.databinding.ActivityMainBinding;
import danielrocha.mobfiq.model.ParamsAPI;
import danielrocha.mobfiq.model.SubCategory;
import danielrocha.mobfiq.view.fragment.ProductsListFragment;
import danielrocha.mobfiq.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding mainActivityBinding;
    private MainViewModel mainViewModel;
    private ProductsListFragment productsListFragment;
    private ParamsAPI paramsAPI = new ParamsAPI();
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(mainActivityBinding.toolbar);
        setupList(mainActivityBinding.recyclerCategories);
        setupObserver(mainViewModel);
        setupProductFragment();

        new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread( () -> mainViewModel.getCategories() );

        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.trim().equals("")) {
                    Toast.makeText(MainActivity.this, getString(R.string.search_without_text_message), Toast.LENGTH_SHORT).show();
                } else {
                    if (!searchView.isIconified()) {
                        searchView.setIconified(true);
                    }
                    myActionMenuItem.collapseActionView();

                    ParamsAPI paramsAPIIntent = new ParamsAPI();
                    paramsAPIIntent.setQuery(query);

                    Intent searchResultIntent = new Intent(MainActivity.this, SearchResultActivity.class);
                    searchResultIntent.putExtra(ProductsListFragment.API_QUERY_EXTRA, paramsAPIIntent);
                    searchResultIntent.putExtra(SearchResultActivity.SEARCH_TITLE_EXTRA, query);

                    startActivity(searchResultIntent);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        return true;
    }

    private void initDataBinding() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(MainActivity.this);
        mainActivityBinding.setMainViewModel(mainViewModel);
    }

    private void setupList(RecyclerView recyclerCategories) {
        CategoriesAdapter adapter = new CategoriesAdapter((view, category) -> {
            Intent subCategoriesIntent = new Intent(this, SubCategoriesActivity.class);
            subCategoriesIntent.putExtra(SubCategoriesActivity.SUB_CATEGORY_LIST_EXTRA, (Serializable) category.getSubCategories());
            subCategoriesIntent.putExtra(SubCategoriesActivity.CATEGORY_EXTRA, category.getName());
            startActivity(subCategoriesIntent);
        });
        recyclerCategories.setAdapter(adapter);
        recyclerCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void setupProductFragment() {
        productsListFragment = ProductsListFragment.newInstance(paramsAPI);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, productsListFragment);
        ft.commit();
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }

    @Override public void update(Observable observable, Object data) {
        if (observable instanceof MainViewModel) {
            CategoriesAdapter categoriesAdapter = (CategoriesAdapter) mainActivityBinding.recyclerCategories.getAdapter();
            MainViewModel mainViewModel = (MainViewModel) observable;
            categoriesAdapter.setCategoryList(mainViewModel.getCategoryList());
        }
    }
}
