package tin.novakovic.foodlocator.ui

import androidx.lifecycle.MutableLiveData
import tin.novakovic.foodlocator.common.DisposingViewModel
import tin.novakovic.foodlocator.R
import tin.novakovic.foodlocator.common.SchedulerProvider
import tin.novakovic.foodlocator.domain.LocationHelper
import tin.novakovic.foodlocator.domain.RestaurantHelper
import tin.novakovic.foodlocator.domain.Restaurant
import tin.novakovic.foodlocator.removeWhiteSpaces
import tin.novakovic.foodlocator.ui.LocationState.*
import tin.novakovic.foodlocator.ui.SearchViewState.*

class SearchViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val restaurantHelper: RestaurantHelper,
    private val locationHelper: LocationHelper
) : DisposingViewModel() {

    val viewState = MutableLiveData<SearchViewState>()

    fun onSearchClicked(outCode: String?) {
        outCode.removeWhiteSpaces(2).also {
            if (it.isNotBlank()) {
                fetchRestaurants(it)
            } else {
                onError(R.string.postcode_error)
            }
        }
    }

    fun onLocationButtonClicked(isLocationPermissionApproved: Boolean) {
        if (isLocationPermissionApproved) {
            getLocation()
        } else {
            viewState.value = LocationNotPermitted
        }
    }

    fun getLocation() {
        viewState.value = Loading
        add(
            locationHelper.getLocation()
                .compose(schedulerProvider.getSchedulers())
                .subscribe({
                    onLocationResultSuccess(it.latitude, it.longitude)
                }, {
                    onLocationError()
                })
        )
    }


    fun onLocationResultSuccess(latitude: Double, longitude: Double) {
        viewState.value = Loading
        add(
            restaurantHelper.fetchRestaurantsByLatLon(latitude, longitude)
                .compose(schedulerProvider.getSchedulers())
                .subscribe({
                    onRestaurantsLoaded(it)
                }, {
                    onError(R.string.network_error)
                })
        )
    }

    private fun fetchRestaurants(outCode: String) {
        viewState.value = Loading
        add(
            restaurantHelper.fetchRestaurantsByOutCode(outCode)
                .compose(schedulerProvider.getSchedulers())
                .subscribe({
                    onRestaurantsLoaded(it)
                }, {
                    onError(R.string.network_error)
                })
        )
    }

    fun onLocationError() {
        onError(R.string.location_error)
    }

    fun onLocationPermissionError() {
        onError(R.string.location_permission_error)
    }

    private fun onRestaurantsLoaded(it: List<Restaurant>) {
        if (it.isEmpty()) onError(R.string.empty_restaurant_list)
        else viewState.value = Presenting(it)
    }

    private fun onError(message: Int) {
        viewState.value = Erroring(message)
    }
}
