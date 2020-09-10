import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import tin.novakovic.foodlocator.data.CuisineType
import tin.novakovic.foodlocator.data.JustEatRepo
import tin.novakovic.foodlocator.data.OldRestaurant
import tin.novakovic.foodlocator.data.RestaurantResponse
import tin.novakovic.foodlocator.domain.Restaurant
import tin.novakovic.foodlocator.domain.RestaurantHelper

class RestaurantHelperUnitTest {

    @Mock
    lateinit var mockJustEatRep: JustEatRepo

    private lateinit var target: RestaurantHelper

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = RestaurantHelper(
            mockJustEatRep
        )
    }

    private val mockRestaurantResponse =
        RestaurantResponse(
            restaurants = listOf(
                OldRestaurant(
                    name = "Rogers Gaff",
                    ratingAverage = 5.5,
                    cuisineTypes = listOf(
                        CuisineType(
                            name = "English"
                        )
                    ),
                    logoUrl = "url01"
                ),
                OldRestaurant(
                    name = "Matilda's Frogs",
                    ratingAverage = 5.5,
                    cuisineTypes = listOf(
                        CuisineType(
                            name = "French"
                        )
                    ),
                    logoUrl = "url02"
                ),
                OldRestaurant(
                    name = "Luigi's Pizzeria",
                    ratingAverage = 5.5,
                    cuisineTypes = listOf(
                        CuisineType(
                            name = "Italian"
                        )
                    ),
                    logoUrl = "url03"
                )
            )
        )

    private val mockRestaurants = listOf(
        Restaurant(
            "Rogers Gaff",
            "5.5",
            "English",
            "url01"
        ),
        Restaurant(
            "Matilda's Frogs",
            "5.5",
            "French",
            "url02"
        ),
        Restaurant(
            "Luigi's Pizzeria",
            "5.5",
            "Italian",
            "url03"
        )
    )

    @Test
    fun fetchRestaurantsByOutCode_returnListOfRestaurant() {
        //given
        val outCode = "sw8"

        whenever(mockJustEatRep.fetchRestaurantsByOutCode(outCode)).thenReturn(Single.just(mockRestaurantResponse))

        //when
        val returnValue = target.fetchRestaurantsByOutCode(outCode).test()

        //then
        val result = returnValue.values().first()
        assertEquals(result, mockRestaurants)

    }

    @Test
    fun fetchRestaurantsByLatLon_returnListOfRestaurant() {
        //given
        val latitude = 1.0
        val longitude = 1.0

        whenever(mockJustEatRep.fetchRestaurantsByLocation(latitude, longitude)).thenReturn(Single.just(mockRestaurantResponse))

        //when
        val returnValue = target.fetchRestaurantsByLatLon(latitude, longitude).test()

        //then
        val result = returnValue.values().first()
        assertEquals(result, mockRestaurants)

    }
}