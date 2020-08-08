package tin.novakovic.foodlocator.core.data_layer

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import tin.novakovic.foodlocator.core.domain_layer.PostCodeResponse

interface JustEatApi {

    @GET("restaurants/bypostcode/{outCode}")
    fun fetchRestaurantsByOutCode(
        @Path("outCode") outCode: String
    ): Single<PostCodeResponse>

}