package tin.novakovic.foodlocator.domain

import io.reactivex.Single
import tin.novakovic.foodlocator.data.JustEatRepo
import tin.novakovic.foodlocator.data.OldRestaurant
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
