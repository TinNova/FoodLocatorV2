package tin.novakovic.foodlocator.ui

import androidx.annotation.StringRes
import tin.novakovic.foodlocator.core.user_case_layer.Restaurant

sealed class MainViewState

data class Presenting(val restaurant: List<Restaurant>) : MainViewState()

data class Error(@StringRes val message: Int) : MainViewState()

object LocationPermitted: MainViewState()

object LocationNotPermitted: MainViewState()

object Loading : MainViewState()
