package tin.novakovic.foodlocator.ui

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tin.novakovic.foodlocator.DisposingViewModel
import tin.novakovic.foodlocator.core.user_case_layer.PostCodeHelper
import tin.novakovic.foodlocator.core.user_case_layer.Restaurant
import tin.novakovic.foodlocator.removeWhiteSpaces
import javax.inject.Inject

class MainViewModel @Inject constructor(private val postCodeHelper: PostCodeHelper) :
    DisposingViewModel() {

    val viewState = MutableLiveData<MainViewState>()

    fun onSearchClicked(outCode: String?) {
        outCode.removeWhiteSpaces(2).also {
            if (it.isNotBlank()) {
                fetchRestaurants(it)
            } else {
                onError("PostCode Not Recognised")
            }
        }
    }

    private fun fetchRestaurants(outCode: String) {

        viewState.value = Loading
        add(
            postCodeHelper.fetchRestaurantsByOutCode(outCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onRestaurantsLoaded(it)
                }, {
                    onError("Network Error, Try Again")
                })
        )
    }

    private fun onRestaurantsLoaded(it: List<Restaurant>) {
        if (it.isEmpty()) onError("PostCode Not Recognised")
        else viewState.value = Presenting(it)
    }

    private fun onError(message: String) {
        viewState.value = Error(message)
    }
}
