package danielrocha.mobfiq.service;

import danielrocha.mobfiq.model.Categories;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by danielrocha on 22/06/17.
 */

public interface CategoriesAPI {
    @GET("StorePreference/CategoryTree")
    Observable<Categories> categories();
}
