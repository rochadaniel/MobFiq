package danielrocha.mobfiq;

import android.content.Context;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;

import danielrocha.mobfiq.databinding.ActivityMainBinding;
import danielrocha.mobfiq.model.Category;
import danielrocha.mobfiq.service.CategoriesAPI;
import danielrocha.mobfiq.viewmodel.MainViewModel;
import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

/**
 * Created by danielrocha on 23/06/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, manifest = Config.NONE, constants = BuildConfig.class, packageName = "danielrocha.mobfiq")
public class MainViewModelTest {
    private MainViewModel mainViewModel;
    @Mock
    private ActivityMainBinding activityMainBinding;
    @Mock
    private CategoriesAPI categoriesAPI;

    @Before
    public void setUpMainViewModelTest() {
        // inject the mocks
        MockitoAnnotations.initMocks(this);
        Context context = ShadowApplication.getInstance().getApplicationContext();
        mainViewModel = new MainViewModel(context);
    }

    @Test
    public void simulateGetCategoriesFromApi() throws Exception {
        List<Category> categoryList = FakeCategoriesGenerator.getCategoryList();

        assertEquals(View.VISIBLE, mainViewModel.isLoading.get());
        assertEquals(View.GONE, mainViewModel.hasError.get());
        assertEquals(View.GONE, mainViewModel.hasList.get());

        doReturn(Observable.just(categoryList)).when(categoriesAPI).categories();
    }

    @Test
    public void ensureTheViewsAreInitializedCorrectly() throws Exception {
        mainViewModel.initViews();
        assertEquals(View.VISIBLE, mainViewModel.isLoading.get());
        assertEquals(View.GONE, mainViewModel.hasList.get());
        assertEquals(View.GONE, mainViewModel.hasError.get());
        assertEquals("", mainViewModel.messageLabel.get());
    }

    @Test
    public void ensureShowErrorCorrectly() throws Exception {
        String message = "Message";
        mainViewModel.showError(message);
        assertEquals(View.GONE, mainViewModel.isLoading.get());
        assertEquals(View.VISIBLE, mainViewModel.hasError.get());
        assertEquals(View.GONE, mainViewModel.hasList.get());
        assertEquals(message, mainViewModel.messageLabel.get());
    }
}
