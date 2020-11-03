package tin.novakovic.foodlocator

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TestSchedulerProviderImpl : SchedulerProvider {

    override fun <T> getSchedulers(): (Single<T>) -> Single<T> {
        return {
            it
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
        }
    }
}