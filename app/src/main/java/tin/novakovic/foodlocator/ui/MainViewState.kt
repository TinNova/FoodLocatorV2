package tin.novakovic.foodlocator.ui

import tin.novakovic.foodlocator.core.user_case_layer.Restaurant

sealed class MainViewState

data class Presenting(val restaurant: List<Restaurant>) : MainViewState()

data class Error(val message: String) : MainViewState()

object Loading : MainViewState()
