package danielrocha.mobfiq.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.adapter.CategoriesAdapter;
import danielrocha.mobfiq.databinding.ActivityMainBinding;
import danielrocha.mobfiq.model.SubCategory;
import danielrocha.mobfiq.view.fragment.ProductsListFragment;
import danielrocha.mobfiq.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding mainActivityBinding;
    private MainViewModel mainViewModel;
    private ProductsListFragment productsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(mainActivityBinding.toolbar);
        setupList(mainActivityBinding.recyclerCategories);
        setupObserver(mainViewModel);

        if (savedInstanceState == null) {
            productsListFragment = (ProductsListFragment)
                    getSupportFragmentManager().findFragmentById(R.id.products_fragment);
        }

        new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread( () -> mainViewModel.getCategories() );

        }).start();
    }

    private void initDataBinding() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(MainActivity.this);
        mainActivityBinding.setMainViewModel(mainViewModel);
    }

    private void setupList(RecyclerView recyclerCategories) {
        CategoriesAdapter adapter = new CategoriesAdapter((view, category) -> {
            Intent subCategoriesIntent = new Intent(this, SubCategoriesActivity.class);
            subCategoriesIntent.putExtra("subCategoryList", (Serializable) category.getSubCategories());
            subCategoriesIntent.putExtra("categoryName", category.getName());
            startActivity(subCategoriesIntent);
        });
        recyclerCategories.setAdapter(adapter);
        recyclerCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
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
