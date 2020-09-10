import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import tin.novakovic.foodlocator.data.JustEatApi
import tin.novakovic.foodlocator.data.JustEatRepo
import tin.novakovic.foodlocator.data.RestaurantResponse

class JustEatRepoUnitTest {

    @Mock
    lateinit var mockJustEatApi: JustEatApi

    private lateinit var target: JustEatRepo

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = JustEatRepo(mockJustEatApi)
    }

    @Test
    fun fetchRestaurantsByOutCode_returnRestaurantResponse() {
        //given
        val outCode = "sw8"
        val mockResponse = mock<RestaurantResponse>()

        whenever(mockJustEatApi.fetchRestaurantsByOutCode(outCode)).thenReturn(Single.just(mockResponse))

        //when
        val returnValue = target.fetchRestaurantsByOutCode(outCode).test()

        //then
        val result = returnValue.values().first()
        assertEquals(result, mockResponse)
    }

    @Test
    fun fetchRestaurantsByLocation_returnRestaurantResponse() {
        //given
        val latitude = 1.0
        val longitude = 1.0
        val mockResponse = mock<RestaurantResponse>()

        whenever(mockJustEatApi.fetchRestaurantsByLocation(latitude, longitude)).thenReturn(Single.just(mockResponse))

        //when
        val returnValue = target.fetchRestaurantsByLocation(latitude, longitude).test()

        //then
        val result = returnValue.values().first()
        assertEquals(result, mockResponse)
    }

}
