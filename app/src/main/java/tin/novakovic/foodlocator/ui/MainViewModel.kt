package tin.novakovic.foodlocator.ui

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tin.novakovic.foodlocator.DisposingViewModel
import tin.novakovic.foodlocator.R
import tin.novakovic.foodlocator.core.user_case_layer.RestaurantHelper
import tin.novakovic.foodlocator.core.user_case_layer.Restaurant
import tin.novakovic.foodlocator.removeWhiteSpaces
import javax.inject.Inject

class MainViewModel @Inject constructor(private val restaurantHelper: RestaurantHelper) :
    DisposingViewModel() {

    val viewState = MutableLiveData<MainViewState>()

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
            viewState.value = LocationPermitted
        } else {
            viewState.value = LocationNotPermitted
        }
    }

    fun onLocationPermissionGranted() {
        viewState.value = LocationPermitted
    }

    fun onLocationResultSuccess(latitude: Double, longitude: Double) {
        viewState.value = Loading
        add(
            restaurantHelper.fetchRestaurantsByLatLon(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
        viewState.value = Error(message)
    }
}
