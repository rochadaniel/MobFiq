package danielrocha.mobfiq.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private List<Product> productList;
    private Context context;

    public ProductsAdapter(Context context) {
        this.productList = Collections.emptyList();
        this.context = context;
    }

    @Override public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductListBinding itemProductListBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product_list,
                        parent, false);
        return new ProductsViewHolder(itemProductListBinding, context);
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
        private Context context;

        public ProductsViewHolder(ItemProductListBinding itemProductListBinding, Context context) {
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
}
