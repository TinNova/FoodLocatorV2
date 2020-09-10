package tin.novakovic.foodlocator.data

import io.reactivex.Single
import javax.inject.Inject

class JustEatRepo @Inject constructor(private val justEatApi: JustEatApi) {

    fun fetchRestaurantsByOutCode(outCode: String): Single<RestaurantResponse> =
        justEatApi.fetchRestaurantsByOutCode(outCode)

    fun fetchRestaurantsByLocation(latitude: Double, longitude: Double): Single<RestaurantResponse> =
        justEatApi.fetchRestaurantsByLocation(latitude, longitude)
}
