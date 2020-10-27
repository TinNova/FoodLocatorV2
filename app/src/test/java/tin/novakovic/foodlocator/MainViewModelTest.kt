package tin.novakovic.foodlocator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import tin.novakovic.foodlocator.domain.Restaurant
import tin.novakovic.foodlocator.domain.RestaurantHelper
import tin.novakovic.foodlocator.ui.*
import tin.novakovic.foodlocator.ui.LocationState.*
import tin.novakovic.foodlocator.ui.MainViewState.*

class MainViewModelUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    lateinit var mockRestaurantHelper: RestaurantHelper

    private lateinit var target: MainViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        target = MainViewModel(mockRestaurantHelper)
    }

    @Test
    fun onSearchClicked_validPostCode_fetchRestaurantsSuccess_assertPresenting() {
        //given
        val outCode = "sw 8  "
        val trimmedOutCode = "sw8"

        val mockRestaurants = mock<List<Restaurant>>()
        whenever(mockRestaurantHelper.fetchRestaurantsByOutCode(trimmedOutCode)).thenReturn(
            Single.just(mockRestaurants)
        )

        //when
        target.onSearchClicked(outCode)

        //then
        assertEquals(
            Presenting(mockRestaurants), target.viewState.value
        )
    }

    @Test
    fun onSearchClicked_validPostCode_fetchRestaurantsSuccessEmptyList_assertError() {
        //given
        val outCode = "sw 8  "
        val trimmedOutCode = "sw8"

        val mockRestaurants = emptyList<Restaurant>()
        whenever(mockRestaurantHelper.fetchRestaurantsByOutCode(trimmedOutCode)).thenReturn(
            Single.just(mockRestaurants)
        )

        //when
        target.onSearchClicked(outCode)

        //then
        assertEquals(
            Erroring(R.string.empty_restaurant_list), target.viewState.value
        )
    }

    @Test
    fun onSearchClicked_validPostCode_fetchRestaurantsFailed_assertError() {
        //given
        val outCode = "sw 8  "
        val trimmedOutCode = "sw8"

        whenever(mockRestaurantHelper.fetchRestaurantsByOutCode(trimmedOutCode)).thenReturn(
            Single.error(Throwable())
        )

        //when
        target.onSearchClicked(outCode)

        //then
        assertEquals(
            Erroring(R.string.network_error), target.viewState.value
        )
    }

    @Test
    fun onSearchClicked_invalidPostCode_assertError() {
        //given
        val outCode = "sw"

        //when
        target.onSearchClicked(outCode)

        //then
        assertEquals(
            Erroring(R.string.postcode_error), target.viewState.value
        )
    }

    @Test
    fun onLocationClicked_fetchRestaurantsByLocationSuccess_assertPresenting() {
        //given
        val latitude = 1.0
        val longitude = 1.0

        val mockRestaurants = mock<List<Restaurant>>()
        whenever(mockRestaurantHelper.fetchRestaurantsByLatLon(latitude, longitude)).thenReturn(
            Single.just(mockRestaurants)
        )

        //when
        target.onLocationResultSuccess(latitude, longitude)

        //then
        assertEquals(
            Presenting(mockRestaurants), target.viewState.value
        )
    }

    @Test
    fun onLocationClicked_fetchRestaurantsByLocationEmptyList_assertError() {
        //given
        val latitude = 1.0
        val longitude = 1.0

        val mockRestaurants = emptyList<Restaurant>()
        whenever(mockRestaurantHelper.fetchRestaurantsByLatLon(latitude, longitude)).thenReturn(
            Single.just(mockRestaurants)
        )

        //when
        target.onLocationResultSuccess(latitude, longitude)

        //then
        assertEquals(
            Erroring(R.string.empty_restaurant_list), target.viewState.value
        )
    }

    @Test
    fun onLocationClicked_fetchRestaurantsByLocationFailed_assertError() {
        //given
        val latitude = 1.0
        val longitude = 1.0

        whenever(mockRestaurantHelper.fetchRestaurantsByLatLon(latitude, longitude)).thenReturn(
            Single.error(Throwable())
        )

        //when
        target.onLocationResultSuccess(latitude, longitude)

        //then
        assertEquals(
            Erroring(R.string.network_error), target.viewState.value
        )
    }

    @Test
    fun onLocationError_assertError() {
        //given
        //when
        target.onLocationError()

        //then
        assertEquals(
            Erroring(R.string.location_error), target.viewState.value
        )
    }

    @Test
    fun onLocationPermissionError_assertError() {
        //given
        //when
        target.onLocationPermissionError()

        //then
        assertEquals(
            Erroring(R.string.location_permission_error), target.viewState.value
        )
    }

    @Test
    fun onLocationButtonClicked_true_assertLocationPermitted() {
        //given
        val isLocationPermissionApproved = true

        //when
        target.onLocationButtonClicked(isLocationPermissionApproved)

        //then
        assertEquals(
            LocationPermitted, target.viewState.value
        )
    }

    @Test
    fun onLocationButtonClicked_false_assertLocationNotPermitted() {
        //given
        val isLocationPermissionApproved = false

        //when
        target.onLocationButtonClicked(isLocationPermissionApproved)

        //then
        assertEquals(
            LocationNotPermitted, target.viewState.value
        )
    }

    @Test
    fun onLocationPermissionGranted_assertLocationPermitted() {
        //given
        //when
        target.onLocationPermissionGranted()

        //then
        assertEquals(LocationPermitted, target.viewState.value)

    }

}
