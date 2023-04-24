package tin.novakovic.foodlocator.domain

import io.reactivex.Single
import io.reactivex.SingleTransformer
import tin.novakovic.foodlocator.data.JustEatRepo
import tin.novakovic.foodlocator.data.OldRestaurant
import tin.novakovic.foodlocator.data.RestaurantResponse

class RestaurantHelper(
    private val justEatRepo: JustEatRepo
) {

    fun fetchRestaurantsByOutCode(outCode: String): Single<List<Restaurant>> =
        justEatRepo.fetchRestaurantsByOutCode(outCode)
            .compose(responseToRestaurant)

    fun fetchRestaurantsByLatLon(latitude: Double, longitude: Double): Single<List<Restaurant>> =
        justEatRepo.fetchRestaurantsByLocation(latitude, longitude)
            .compose(responseToRestaurant)

    private val responseToRestaurant =
        SingleTransformer<RestaurantResponse, List<Restaurant>> { restaurantResponse ->
            restaurantResponse
                .map { it.restaurants }
                .flattenAsObservable { it }
                .map { mapResponseToRestaurant(it) }
                .toList()
        }

    private fun mapResponseToRestaurant(it: OldRestaurant) =
        Restaurant(
            name = it.name,
            rating = it.ratingAverage.toString(),
            foodType = it.cuisineTypes[0].name,
            logo = it.logoUrl
        )
}
