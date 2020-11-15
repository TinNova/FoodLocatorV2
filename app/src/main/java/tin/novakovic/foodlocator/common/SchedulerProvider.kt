package tin.novakovic.foodlocator.common

import io.reactivex.Single

interface SchedulerProvider {
    fun <T> getSchedulers(): (Single<T>) -> Single<T>
}