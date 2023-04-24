package tin.novakovic.foodlocator.data

import io.reactivex.Flowable
import io.reactivex.Single
import tin.novakovic.foodlocator.data.LocationModel

interface LocationProvider {
    fun getLocation(): Flowable<LocationModel>
}
