package tin.novakovic.foodlocator.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
