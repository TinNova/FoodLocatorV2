package tin.novakovic.foodlocator

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProviderImpl :
    SchedulerProvider {
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun ui(): Scheduler = Schedulers.trampoline()
}