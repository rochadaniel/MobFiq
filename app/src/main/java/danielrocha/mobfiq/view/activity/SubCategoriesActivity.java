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

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.adapter.SubCategoriesAdapter;
import danielrocha.mobfiq.databinding.ActivitySubCategoriesBinding;
import danielrocha.mobfiq.model.SubCategory;
import danielrocha.mobfiq.viewmodel.SubCategoriesViewModel;

public class SubCategoriesActivity extends AppCompatActivity {

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

            if (intent.getSerializableExtra("subCategoryList") != null)
                subCategoryList = (List<SubCategory>) getIntent().getSerializableExtra("subCategoryList");

            if(intent.getStringExtra("categoryName") != null)
                categoryName = intent.getStringExtra("categoryName");
        }
        initToolbar();
        setupList(subCategoryList, activitySubCategoriesBinding.recyclerSubCategories);

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
        SubCategoriesAdapter adapter = new SubCategoriesAdapter(subCategoryList, (view, subCategory) ->
                Toast.makeText(SubCategoriesActivity.this, subCategory.getName(),
                        Toast.LENGTH_SHORT).show()
        );
        recyclerCategories.setAdapter(adapter);
        recyclerCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
