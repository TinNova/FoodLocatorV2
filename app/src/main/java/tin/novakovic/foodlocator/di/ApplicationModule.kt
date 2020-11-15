package tin.novakovic.foodlocator.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tin.novakovic.foodlocator.common.LocationDataSource
import tin.novakovic.foodlocator.data.LocationProvider
import tin.novakovic.foodlocator.common.SchedulerProvider
import tin.novakovic.foodlocator.common.SchedulerProviderImpl
import tin.novakovic.foodlocator.data.JustEatRepo
import tin.novakovic.foodlocator.data.LocationRepo
import tin.novakovic.foodlocator.domain.LocationHelper
import tin.novakovic.foodlocator.domain.RestaurantHelper
import tin.novakovic.foodlocator.ui.MainAdapter
import tin.novakovic.foodlocator.ui.MainViewModel

val applicationModule = module {

    /** App Module*/
    factory {
        MainAdapter()
    }

    single<SchedulerProvider> {
        SchedulerProviderImpl()
    }

    single<LocationProvider>{
        LocationDataSource(get())
    }

    viewModel {
        MainViewModel(get(), get(), get())
    }

    /** Domain Module */
    factory {
        RestaurantHelper(get())
    }

    factory {
        LocationHelper(get())
    }

    /** Data Module */
    single {
        RetrofitFactory.makeRetrofitService()
    }

    factory {
        JustEatRepo(get())
    }

    factory {
        LocationRepo(get())
    }
}