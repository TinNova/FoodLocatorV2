package tin.novakovic.foodlocator

import android.app.Application
import tin.novakovic.foodlocator.di.AppComponent
import tin.novakovic.foodlocator.di.DaggerAppComponent

open class App : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}