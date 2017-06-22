package danielrocha.mobfiq.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.databinding.ItemCategoryListBinding;
import danielrocha.mobfiq.listener.CategoryOnItemClickListener;
import danielrocha.mobfiq.model.Category;
import danielrocha.mobfiq.viewmodel.ItemCategoriesViewModel;

/**
 * Created by danielrocha on 22/06/17.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private List<Category> categoryList;
    private CategoryOnItemClickListener categoryOnItemClickListener;

    public CategoriesAdapter(CategoryOnItemClickListener categoryOnItemClickListener) {
        this.categoryList = Collections.emptyList();
        this.categoryOnItemClickListener = categoryOnItemClickListener;
    }

    @Override public CategoriesAdapter.CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryListBinding itemCategoryListBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_category_list,
                        parent, false);
        return new CategoriesAdapter.CategoriesViewHolder(itemCategoryListBinding);
    }

    @Override public void onBindViewHolder(CategoriesAdapter.CategoriesViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.itemCategoryListBinding.itemCategory.setOnClickListener( v ->
            categoryOnItemClickListener.onItemClick(v, category)
        );
        holder.bindCategory(category);
    }

    @Override public int getItemCount() {
        return categoryList.size();
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryListBinding itemCategoryListBinding;

        public CategoriesViewHolder(ItemCategoryListBinding itemCategoryListBinding) {
            super(itemCategoryListBinding.itemCategory);
            this.itemCategoryListBinding = itemCategoryListBinding;
        }

        void bindCategory(Category category) {
            if (itemCategoryListBinding.getItemViewModel() == null) {
                itemCategoryListBinding.setItemViewModel(
                        new ItemCategoriesViewModel(category, itemView.getContext()));
            } else {
                itemCategoryListBinding.getItemViewModel().setCategory(category);
            }
            itemCategoryListBinding.executePendingBindings();
        }
    }
}
