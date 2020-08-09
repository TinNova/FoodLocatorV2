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
import tin.novakovic.foodlocator.core.user_case_layer.Restaurant
import tin.novakovic.foodlocator.core.user_case_layer.RestaurantHelper
import tin.novakovic.foodlocator.ui.*

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
            Presenting(mockRestaurants)
            , target.viewState.value
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
            Error(R.string.empty_restaurant_list)
            , target.viewState.value
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
            Error(R.string.network_error)
            , target.viewState.value
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
            Error(R.string.postcode_error)
            , target.viewState.value
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
        target.onLocationClicked(latitude, longitude)

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
        target.onLocationClicked(latitude, longitude)

        //then
        assertEquals(
            Error(R.string.empty_restaurant_list)
            , target.viewState.value
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
        target.onLocationClicked(latitude, longitude)

        //then
        assertEquals(
            Error(R.string.network_error)
            , target.viewState.value
        )
    }

    @Test
    fun onLocationError_assertError() {
        //given
        //when
        target.onLocationError()

        //then
        assertEquals(
            Error(R.string.location_error)
            , target.viewState.value
        )
    }
}