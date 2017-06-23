package danielrocha.mobfiq.service;

import danielrocha.mobfiq.model.ItemResult;
import danielrocha.mobfiq.model.ParamsAPI;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by danielrocha on 22/06/17.
 */

public interface ProductsAPI {
    /**
     * {
     *  "Query" : "ShapeNow/Beleza/Aparador-de-Pelos",
     *  "Offset": 0,
     *  "Size": 10
     * }
     * @param params
     * @return
     */
    @POST("Search/Criteria")
    Observable<ItemResult> products(@Body ParamsAPI params);
}
