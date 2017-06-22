package danielrocha.mobfiq.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.databinding.ItemProductListBinding;
import danielrocha.mobfiq.model.Product;
import danielrocha.mobfiq.viewmodel.ItemProductsViewModel;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private List<Product> productList;

    public ProductsAdapter() {
        this.productList = Collections.emptyList();
    }

    @Override public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductListBinding itemProductListBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product_list,
                        parent, false);
        return new ProductsViewHolder(itemProductListBinding);
    }

    @Override public void onBindViewHolder(ProductsViewHolder holder, int position) {
        holder.bindProduct(productList.get(position));
    }

    @Override public int getItemCount() {
        return productList.size();
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {
        ItemProductListBinding itemProductListBinding;

        public ProductsViewHolder(ItemProductListBinding itemProductListBinding) {
            super(itemProductListBinding.itemProduct);
            this.itemProductListBinding = itemProductListBinding;
        }

        void bindProduct(Product product) {
            if (itemProductListBinding.getItemViewModel() == null) {
                itemProductListBinding.setItemViewModel(
                        new ItemProductsViewModel(product, itemView.getContext()));
            } else {
                itemProductListBinding.getItemViewModel().setProduct(product);
            }
            itemProductListBinding.executePendingBindings();
        }
    }
}
