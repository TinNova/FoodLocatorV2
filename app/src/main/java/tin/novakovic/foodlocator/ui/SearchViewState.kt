package tin.novakovic.foodlocator.ui

import androidx.annotation.StringRes
import tin.novakovic.foodlocator.domain.Restaurant

sealed class SearchViewState {

    data class Presenting(val restaurant: List<Restaurant>) : SearchViewState()

    data class Erroring(@StringRes val message: Int) : SearchViewState()

    object Loading : SearchViewState()

}

sealed class LocationState : SearchViewState() {

    object LocationPermitted : LocationState()

    object LocationNotPermitted : LocationState()

}