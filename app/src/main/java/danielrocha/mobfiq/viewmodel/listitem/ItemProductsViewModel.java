package danielrocha.mobfiq.viewmodel.listitem;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import danielrocha.mobfiq.R;
import danielrocha.mobfiq.model.Product;

/**
 * Created by danielrocha on 22/06/17.
 */

public class ItemProductsViewModel extends BaseObservable {

    private Product product;
    private Context context;
    public ObservableInt hasPromotion = new ObservableInt(View.GONE);

    public ItemProductsViewModel(Product product, Context context) {
        this.product = product;
        this.context = context;
    }

    public String getName() {
        return product.getName();
    }

    public String getModelImageUrl() {
        return product.getSkus().get(0).getImages().get(0).getImageUrl();
    }

    public String getPromotionValue() {
        double listPrice = product.getSkus().get(0).getSellers().get(0).getListPrice();
        double price = product.getSkus().get(0).getSellers().get(0).getPrice();
        double percent = (((listPrice - price )/ listPrice) * 100 );
        return String.format(java.util.Locale.US, "%.1f%%", Math.ceil(percent));
    }

    public boolean hasPromotionFunction() {
        double listPrice = product.getSkus().get(0).getSellers().get(0).getListPrice();
        double price = product.getSkus().get(0).getSellers().get(0).getPrice();
        boolean hasP = listPrice - price != 0;
        hasPromotion.set(hasP ? View.VISIBLE : View.GONE);
        return hasP;
    }

    public String getListPrice() {
        return "R$" + product.getSkus().get(0).getSellers().get(0).getListPrice();
    }

    public String getPrice() {
        return "R$" + product.getSkus().get(0).getSellers().get(0).getPrice();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide
                .with(imageView.getContext())
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.image_placeholder)
                .dontAnimate()
                .into(
                        new BitmapImageViewTarget(imageView) {
                            @Override
                            public void onLoadStarted(Drawable placeholder) {
                                super.onLoadStarted(placeholder);
                                imageView.setImageDrawable(placeholder);
                            }

                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                super.onResourceReady(resource, glideAnimation);
                                imageView.setImageBitmap(resource);
                            }

                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                super.onLoadFailed(e, errorDrawable);
                                imageView.setImageResource(R.drawable.no_image_available);
                            }
                        });

    }


    public void setProduct(Product product) {
        this.product = product;
        notifyChange();
    }
}
