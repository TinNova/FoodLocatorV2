package tin.novakovic.foodlocator

import io.reactivex.Single

interface SchedulerProvider {
    fun <T> getSchedulers(): (Single<T>) -> Single<T>
}