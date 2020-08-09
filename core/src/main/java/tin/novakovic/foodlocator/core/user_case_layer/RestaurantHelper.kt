package tin.novakovic.foodlocator.core.user_case_layer

import io.reactivex.Single
import tin.novakovic.foodlocator.core.data_layer.JustEatRepo
import tin.novakovic.foodlocator.core.domain_layer.OldRestaurant
import javax.inject.Inject

class RestaurantHelper @Inject constructor(private val justEatRepo: JustEatRepo) {

    fun fetchRestaurantsByOutCode(outCode: String): Single<List<Restaurant>> =
        justEatRepo.fetchRestaurantsByOutCode(outCode)
            .map { it.restaurants }
            .flattenAsObservable { it }
            .map { mapResponseToRestaurant(it) }
            .toList()

    fun fetchRestaurantsByLatLon(latitude: Double, longitude: Double): Single<List<Restaurant>> =
        justEatRepo.fetchRestaurantsByLocation(latitude, longitude)
            .map { it.restaurants }
            .flattenAsObservable { it }
            .map { mapResponseToRestaurant(it) }
            .toList()

    private fun mapResponseToRestaurant(it: OldRestaurant) =
        Restaurant(
            name = it.name,
            rating = it.ratingAverage.toString(),
            foodType = it.cuisineTypes[0].name,
            logo = it.logoUrl
        )
}
