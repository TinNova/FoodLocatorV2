package tin.novakovic.foodlocator.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tin.novakovic.foodlocator.common.ContextWrapper
import tin.novakovic.foodlocator.common.LocationDataSource
import tin.novakovic.foodlocator.data.LocationProvider
import tin.novakovic.foodlocator.common.SchedulerProvider
import tin.novakovic.foodlocator.common.SchedulerProviderImpl
import tin.novakovic.foodlocator.data.JustEatRepo
import tin.novakovic.foodlocator.data.LocationRepo
import tin.novakovic.foodlocator.domain.LocationHelper
import tin.novakovic.foodlocator.domain.RestaurantHelper
import tin.novakovic.foodlocator.ui.SearchAdapter
import tin.novakovic.foodlocator.ui.SearchViewModel

val applicationModule = module {

    /** App Module*/
    factory {
        SearchAdapter()
    }

    single<SchedulerProvider> {
        SchedulerProviderImpl()
    }

    single<LocationProvider> {
        LocationDataSource(get())
    }

    viewModel {
        SearchViewModel(get(), get(), get())
    }

    single {
        ContextWrapper(get())
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