package tin.novakovic.foodlocator.data

import io.reactivex.Flowable

class LocationRepo(private val locationProvider: LocationProvider): LocationProvider {

    override fun getLocation(): Flowable<LocationModel> {
        return locationProvider.getLocation()
    }

}
