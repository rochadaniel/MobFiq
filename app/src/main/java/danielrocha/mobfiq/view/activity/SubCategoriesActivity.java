package danielrocha.mobfiq.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.adapter.SubCategoriesAdapter;
import danielrocha.mobfiq.databinding.ActivitySubCategoriesBinding;
import danielrocha.mobfiq.model.ParamsAPI;
import danielrocha.mobfiq.model.SubCategory;
import danielrocha.mobfiq.view.fragment.ProductsListFragment;
import danielrocha.mobfiq.viewmodel.SubCategoriesViewModel;

public class SubCategoriesActivity extends AppCompatActivity {
    public static final String CATEGORY_EXTRA = "CATEGORY_EXTRA", SUB_CATEGORY_LIST_EXTRA = "subCategoryList";
    private ActivitySubCategoriesBinding activitySubCategoriesBinding;
    private SubCategoriesViewModel subCategoriesViewModel;
    private List<SubCategory> subCategoryList = new ArrayList<>();
    private String categoryName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        if(savedInstanceState == null) {
            Intent intent = getIntent();

            if (intent.getSerializableExtra(SUB_CATEGORY_LIST_EXTRA) != null)
                subCategoryList = (List<SubCategory>) getIntent().getSerializableExtra(SUB_CATEGORY_LIST_EXTRA);

            if(intent.getStringExtra(CATEGORY_EXTRA) != null)
                categoryName = intent.getStringExtra(CATEGORY_EXTRA);
        } else {
            subCategoryList = (List<SubCategory>) savedInstanceState.getSerializable(SUB_CATEGORY_LIST_EXTRA);
            categoryName = savedInstanceState.getString(CATEGORY_EXTRA);
        }
        initToolbar();
        setupList(subCategoryList, activitySubCategoriesBinding.recyclerSubCategories);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(CATEGORY_EXTRA, categoryName);
        outState.putSerializable(SUB_CATEGORY_LIST_EXTRA, (ArrayList<SubCategory>)subCategoryList);
        super.onSaveInstanceState(outState);
    }

    private void initDataBinding() {
        activitySubCategoriesBinding = DataBindingUtil.setContentView(this, R.layout.activity_sub_categories);
        subCategoriesViewModel = new SubCategoriesViewModel(SubCategoriesActivity.this);
        activitySubCategoriesBinding.setSubCategoriesViewModel(subCategoriesViewModel);
    }

    private void initToolbar() {
        activitySubCategoriesBinding.toolbar.setTitle(categoryName);
        setSupportActionBar(activitySubCategoriesBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activitySubCategoriesBinding.toolbar.setNavigationOnClickListener( v -> {
            onBackPressed();
        });
    }


    private void setupList(List<SubCategory> subCategoryList, RecyclerView recyclerCategories) {
        SubCategoriesAdapter adapter = new SubCategoriesAdapter(subCategoryList, (view, subCategory) -> {
            Intent searchResultIntent = new Intent(SubCategoriesActivity.this, SearchResultActivity.class);

            ParamsAPI paramsAPI = new ParamsAPI();
            paramsAPI.setQuery(subCategory.getRedirect().getSearchCriteria().getApiQuery());
            searchResultIntent.putExtra(ProductsListFragment.API_QUERY_EXTRA, paramsAPI);
            searchResultIntent.putExtra(SearchResultActivity.SEARCH_TITLE_EXTRA, subCategory.getName());

            startActivity(searchResultIntent);
        });
        recyclerCategories.setAdapter(adapter);
        recyclerCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

}
