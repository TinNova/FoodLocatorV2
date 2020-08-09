package tin.novakovic.foodlocator.core.data_layer

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tin.novakovic.foodlocator.core.domain_layer.RestaurantResponse

interface JustEatApi {

    @GET("restaurants/bypostcode/{outCode}")
    fun fetchRestaurantsByOutCode(
        @Path("outCode") outCode: String
    ): Single<RestaurantResponse>

    @GET("restaurants/bylatlong")
    fun fetchRestaurantsByLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Single<RestaurantResponse>
}
