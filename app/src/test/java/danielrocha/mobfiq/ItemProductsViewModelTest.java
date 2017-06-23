package danielrocha.mobfiq;

import android.content.Context;
import android.databinding.Observable;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import danielrocha.mobfiq.model.Product;
import danielrocha.mobfiq.viewmodel.listitem.ItemProductsViewModel;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by danielrocha on 23/06/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, manifest = Config.NONE, constants = BuildConfig.class, packageName = "danielrocha.mobfiq")
public class ItemProductsViewModelTest {
    private ItemProductsViewModel itemProductsViewModel;
    private Product product;
    private Context context;

    @Before
    public void setUpItemTravelPackageViewModelTest() {
        product = FakeProductsGenerator.getProduct(1);
        context = ShadowApplication.getInstance().getApplicationContext();
        itemProductsViewModel = new ItemProductsViewModel(product, context);
    }

    @Test
    public void verifyPromotionVisibility() throws Exception {
        assertEquals(itemProductsViewModel.hasPromotionFunction() ? View.VISIBLE : View.GONE, itemProductsViewModel.hasPromotion.get());
    }

    @Test
    public void shouldGetName() throws Exception {
        assertEquals(product.getName(), itemProductsViewModel.getName());
    }

    @Test
    public void shouldNotifyPropertyChangeWhenSetItem() throws Exception {
        Product prod = new Product();
        ItemProductsViewModel itemProductsViewModel = new ItemProductsViewModel(prod, context);
        Observable.OnPropertyChangedCallback mockCallback =
                mock(Observable.OnPropertyChangedCallback.class);
        itemProductsViewModel.addOnPropertyChangedCallback(mockCallback);
        itemProductsViewModel.setProduct(prod);
        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }
}
