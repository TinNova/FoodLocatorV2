package tin.novakovic.foodlocator.core.user_case_layer

import io.reactivex.Single
import tin.novakovic.foodlocator.core.data_layer.JustEatRepo
import javax.inject.Inject

class PostCodeHelper @Inject constructor(private val justEatRepo: JustEatRepo) {

    fun fetchRestaurantsByOutCode(outCode: String): Single<List<Restaurant>> =
        justEatRepo.fetchRestaurantsByOutCode(outCode)
            .map { it.restaurants }
            .flattenAsObservable { it }
            .map {
                Restaurant(
                    name = it.name,
                    rating = it.ratingAverage.toString(),
                    foodType = it.cuisineTypes[0].name,
                    logo = it.logoUrl
                )
            }.toList()
}
