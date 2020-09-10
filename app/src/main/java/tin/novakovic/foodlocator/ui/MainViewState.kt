package tin.novakovic.foodlocator.ui

import androidx.annotation.StringRes
import tin.novakovic.foodlocator.domain.Restaurant

sealed class MainViewState {

    data class Presenting(val restaurant: List<Restaurant>) : MainViewState()

    data class Erroring(@StringRes val message: Int) : MainViewState()

    object Loading : MainViewState()

}

sealed class LocationState : MainViewState() {

    object LocationPermitted : LocationState()

    object LocationNotPermitted : LocationState()

}