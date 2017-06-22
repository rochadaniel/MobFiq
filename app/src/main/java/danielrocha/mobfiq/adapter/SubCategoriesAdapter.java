package danielrocha.mobfiq.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.databinding.ItemSubCategoryListBinding;
import danielrocha.mobfiq.listener.SubCategoryOnItemClickListener;
import danielrocha.mobfiq.model.SubCategory;
import danielrocha.mobfiq.viewmodel.listitem.ItemSubCategoriesViewModel;

/**
 * Created by danielrocha on 22/06/17.
 */

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.SubCategoriesViewHolder> {

    private List<SubCategory> subCategoryList;
    private SubCategoryOnItemClickListener subCategoryOnItemClickListener;

    public SubCategoriesAdapter(List<SubCategory> subCategoryList,
                                SubCategoryOnItemClickListener subCategoryOnItemClickListener) {
        this.subCategoryList = subCategoryList;
        this.subCategoryOnItemClickListener = subCategoryOnItemClickListener;
    }

    @Override public SubCategoriesAdapter.SubCategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSubCategoryListBinding itemSubCategoryListBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_sub_category_list,
        parent, false);
        return new SubCategoriesAdapter.SubCategoriesViewHolder(itemSubCategoryListBinding);
    }

    @Override public void onBindViewHolder(SubCategoriesAdapter.SubCategoriesViewHolder holder, int position) {
        SubCategory subCategory = subCategoryList.get(position);
        holder.itemSubCategoryListBinding.itemSubCategory.setOnClickListener( v ->
            subCategoryOnItemClickListener.onItemClick(v, subCategory)
        );
        holder.bindSubCategory(subCategory);
    }

    @Override public int getItemCount() {
        return subCategoryList.size();
    }

    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
        notifyDataSetChanged();
    }

    public static class SubCategoriesViewHolder extends RecyclerView.ViewHolder {
        ItemSubCategoryListBinding itemSubCategoryListBinding;

        public SubCategoriesViewHolder(ItemSubCategoryListBinding itemSubCategoryListBinding) {
            super(itemSubCategoryListBinding.itemSubCategory);
            this.itemSubCategoryListBinding = itemSubCategoryListBinding;
        }

        void bindSubCategory(SubCategory subCategory) {
            if (itemSubCategoryListBinding.getItemViewModel() == null) {
                itemSubCategoryListBinding.setItemViewModel(
                        new ItemSubCategoriesViewModel(subCategory, itemView.getContext()));
            } else {
                itemSubCategoryListBinding.getItemViewModel().setSubCategory(subCategory);
            }
            itemSubCategoryListBinding.executePendingBindings();
        }
    }
}
