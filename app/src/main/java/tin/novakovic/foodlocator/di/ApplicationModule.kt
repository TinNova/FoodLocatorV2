package tin.novakovic.foodlocator.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tin.novakovic.foodlocator.SchedulerProvider
import tin.novakovic.foodlocator.SchedulerProviderImpl
import tin.novakovic.foodlocator.data.JustEatRepo
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

    viewModel {
        MainViewModel(get(), get())
    }

    /** Domain Module */
    factory {
        RestaurantHelper(get())
    }


    /** Data Module */
    single {
        RetrofitFactory.makeRetrofitService()
    }

    factory {
        JustEatRepo(get())
    }
}