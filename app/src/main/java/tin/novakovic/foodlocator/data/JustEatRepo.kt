package tin.novakovic.foodlocator.data

import io.reactivex.Single

class JustEatRepo (private val justEatApi: JustEatApi) {

    fun fetchRestaurantsByOutCode(outCode: String): Single<RestaurantResponse> =
        justEatApi.fetchRestaurantsByOutCode(outCode)

    fun fetchRestaurantsByLocation(latitude: Double, longitude: Double): Single<RestaurantResponse> =
        justEatApi.fetchRestaurantsByLocation(latitude, longitude)
}
