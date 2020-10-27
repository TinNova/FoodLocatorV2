package tin.novakovic.foodlocator.di.modules

import dagger.Binds
import dagger.Module
import dagger.Reusable
import tin.novakovic.foodlocator.SchedulerProvider
import tin.novakovic.foodlocator.SchedulerProviderImpl

@Module
abstract class SchedulersModule {

    @Binds
    @Reusable
    abstract fun provideSchedulers(schedulersImpl: SchedulerProviderImpl) : SchedulerProvider
}