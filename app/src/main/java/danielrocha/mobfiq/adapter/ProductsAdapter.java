package danielrocha.mobfiq.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.databinding.ItemProductListBinding;
import danielrocha.mobfiq.model.Product;
import danielrocha.mobfiq.viewmodel.listitem.ItemProductsViewModel;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> productList;
    private Context context;
    public final int VIEW_TYPE_ITEM = 0;
    public final int VIEW_TYPE_LOADING = 1;
    public boolean isLoading;

    public ProductsAdapter(Context context) {
        this.productList = Collections.emptyList();
        this.context = context;
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            ItemProductListBinding itemProductListBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product_list,
                            parent, false);
            return new ProductsViewHolder(itemProductListBinding, context);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading_product_list, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ProductsViewHolder) {
            ProductsViewHolder productsViewHolder = (ProductsViewHolder)holder;
            productsViewHolder.bindProduct(productList.get(position));
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override public int getItemCount() {
        return productList.size();
    }

    @Override public int getItemViewType(int position) {
        return productList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    static class ProductsViewHolder extends RecyclerView.ViewHolder {
        ItemProductListBinding itemProductListBinding;
        private Context context;

        ProductsViewHolder(ItemProductListBinding itemProductListBinding, Context context) {
            super(itemProductListBinding.itemProduct);
            this.itemProductListBinding = itemProductListBinding;
            this.context = context;
        }

        void bindProduct(Product product) {
            if (itemProductListBinding.getItemViewModel() == null) {
                itemProductListBinding.setItemViewModel(
                        new ItemProductsViewModel(product, itemView.getContext()));
            } else {
                itemProductListBinding.getItemViewModel().setProduct(product);
            }
            configPromotionStyles();
            itemProductListBinding.executePendingBindings();
        }

        private void configPromotionStyles() {
            itemProductListBinding.textListPrice.setPaintFlags(itemProductListBinding.textListPrice.getPaintFlags() |
                    Paint.STRIKE_THRU_TEXT_FLAG);

            if(itemProductListBinding.getItemViewModel().hasPromotionFunction()) {
                itemProductListBinding.textPrice.setTextColor(context.getResources().getColor(R.color.green));
            } else {
                itemProductListBinding.textListPrice.setPaintFlags(itemProductListBinding.textListPrice.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                itemProductListBinding.textPrice.setTextColor(context.getResources().getColor(R.color.gray));
            }
            itemProductListBinding.getItemViewModel().hasPromotionFunction();
        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}
