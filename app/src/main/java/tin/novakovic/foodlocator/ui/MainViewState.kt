package tin.novakovic.foodlocator.ui

import androidx.annotation.StringRes
import tin.novakovic.foodlocator.core.user_case_layer.Restaurant

sealed class MainViewState {

    data class Presenting(val restaurant: List<Restaurant>) : MainViewState()

    data class Error(@StringRes val message: Int = 0) : MainViewState()

    object Loading : MainViewState()

}

sealed class LocationState: MainViewState() {

    object LocationPermitted : LocationState()

    object LocationNotPermitted : LocationState()

}