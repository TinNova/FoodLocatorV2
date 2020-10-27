package tin.novakovic.foodlocator.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tin.novakovic.foodlocator.ui.MainActivity
import tin.novakovic.foodlocator.di.modules.ApiModule
import tin.novakovic.foodlocator.di.modules.AppModule
import tin.novakovic.foodlocator.di.modules.SchedulersModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, SchedulersModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)

}