package tin.novakovic.foodlocator

import android.os.AsyncTask
import androidx.test.runner.AndroidJUnitRunner
import io.reactivex.Scheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

/**
 * Used to ensure that RxJava requests are awaited for by Espresso
 */
class EspressoRxJUnitRunner : AndroidJUnitRunner() {
    override fun onStart() {
        val asyncTaskScheduler = object : Function1<Scheduler, Scheduler> {
            override fun invoke(scheduler: Scheduler): Scheduler {
                return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)
            }
        }

        RxJavaPlugins.setIoSchedulerHandler(asyncTaskScheduler)
        RxJavaPlugins.setComputationSchedulerHandler(asyncTaskScheduler)
        RxJavaPlugins.setNewThreadSchedulerHandler(asyncTaskScheduler)

        super.onStart()
    }
}