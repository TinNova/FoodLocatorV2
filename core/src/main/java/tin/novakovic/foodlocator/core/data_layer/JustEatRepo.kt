package tin.novakovic.foodlocator.core.data_layer

import io.reactivex.Single
import tin.novakovic.foodlocator.core.domain_layer.PostCodeResponse
import javax.inject.Inject

class JustEatRepo @Inject constructor(private val justEatApi: JustEatApi) {

    fun fetchRestaurantsByOutCode(outCode: String): Single<PostCodeResponse> =
        justEatApi.fetchRestaurantsByOutCode(outCode)

}
