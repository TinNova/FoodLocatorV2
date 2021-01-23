package tin.novakovic.foodlocator.domain

import java.io.Serializable

data class Restaurant(
    val name: String = "",
    val rating: String = "",
    val foodType: String = "",
    val logo: String = ""
): Serializable
