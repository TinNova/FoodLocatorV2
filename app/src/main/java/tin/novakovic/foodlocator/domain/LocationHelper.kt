package tin.novakovic.foodlocator.domain

import io.reactivex.Single
import tin.novakovic.foodlocator.data.LocationModel
import tin.novakovic.foodlocator.data.LocationRepo

class LocationHelper(
    private val locationRepo: LocationRepo
) {

    fun getLocation(): Single<LocationModel> {
        return locationRepo.getLocation()
            .firstOrError()
    }
}
