package tin.novakovic.foodlocator.core.domain_layer
import com.google.gson.annotations.SerializedName


data class PostCodeResponse(
    @SerializedName("Area")
    val area: String = "",
    @SerializedName("MetaData")
    val metaData: MetaData = MetaData(),
    @SerializedName("Restaurants")
    val restaurants: List<Restaurant> = listOf(),
    @SerializedName("RestaurantSets")
    val restaurantSets: List<RestaurantSet> = listOf(),
    @SerializedName("CuisineSets")
    val cuisineSets: List<CuisineSet> = listOf(),
    @SerializedName("Views")
    val views: List<View> = listOf(),
    @SerializedName("Dishes")
    val dishes: List<Any> = listOf(),
    @SerializedName("ShortResultText")
    val shortResultText: String = ""
)

data class MetaData(
    @SerializedName("CanonicalName")
    val canonicalName: String = "",
    @SerializedName("District")
    val district: String = "",
    @SerializedName("Postcode")
    val postcode: String = "",
    @SerializedName("Area")
    val area: String = "",
    @SerializedName("Latitude")
    val latitude: Double = 0.0,
    @SerializedName("Longitude")
    val longitude: Double = 0.0,
    @SerializedName("CuisineDetails")
    val cuisineDetails: List<CuisineDetail> = listOf(),
    @SerializedName("ResultCount")
    val resultCount: Int = 0,
    @SerializedName("SearchedTerms")
    val searchedTerms: Any = Any(),
    @SerializedName("TagDetails")
    val tagDetails: List<TagDetail> = listOf()
)

data class CuisineDetail(
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("SeoName")
    val seoName: String = "",
    @SerializedName("Total")
    val total: Int = 0
)

data class TagDetail(
    @SerializedName("BackgroundColour")
    val backgroundColour: String = "",
    @SerializedName("Colour")
    val colour: String = "",
    @SerializedName("DisplayName")
    val displayName: String = "",
    @SerializedName("Key")
    val key: String = "",
    @SerializedName("Priority")
    val priority: Int = 0
)

data class Restaurant(
    @SerializedName("Id")
    val id: Int = 0,
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("UniqueName")
    val uniqueName: String = "",
    @SerializedName("Address")
    val address: Address = Address(),
    @SerializedName("City")
    val city: String = "",
    @SerializedName("Postcode")
    val postcode: String = "",
    @SerializedName("Latitude")
    val latitude: Double = 0.0,
    @SerializedName("Longitude")
    val longitude: Double = 0.0,
    @SerializedName("Rating")
    val rating: Rating = Rating(),
    @SerializedName("RatingStars")
    val ratingStars: Double = 0.0,
    @SerializedName("NumberOfRatings")
    val numberOfRatings: Int = 0,
    @SerializedName("RatingAverage")
    val ratingAverage: Double = 0.0,
    @SerializedName("Description")
    val description: String = "",
    @SerializedName("Url")
    val url: String = "",
    @SerializedName("LogoUrl")
    val logoUrl: String = "",
    @SerializedName("IsTestRestaurant")
    val isTestRestaurant: Boolean = false,
    @SerializedName("IsHalal")
    val isHalal: Boolean = false,
    @SerializedName("IsNew")
    val isNew: Boolean = false,
    @SerializedName("ReasonWhyTemporarilyOffline")
    val reasonWhyTemporarilyOffline: String = "",
    @SerializedName("DriveDistance")
    val driveDistance: Double = 0.0,
    @SerializedName("DriveInfoCalculated")
    val driveInfoCalculated: Boolean = false,
    @SerializedName("IsCloseBy")
    val isCloseBy: Boolean = false,
    @SerializedName("OfferPercent")
    val offerPercent: Double = 0.0,
    @SerializedName("NewnessDate")
    val newnessDate: String = "",
    @SerializedName("OpeningTime")
    val openingTime: String = "",
    @SerializedName("OpeningTimeIso")
    val openingTimeIso: String = "",
    @SerializedName("OpeningTimeLocal")
    val openingTimeLocal: String = "",
    @SerializedName("DeliveryOpeningTimeLocal")
    val deliveryOpeningTimeLocal: String = "",
    @SerializedName("DeliveryOpeningTime")
    val deliveryOpeningTime: String = "",
    @SerializedName("DeliveryStartTime")
    val deliveryStartTime: String = "",
    @SerializedName("DeliveryWorkingTimeMinutes")
    val deliveryWorkingTimeMinutes: Int = 0,
    @SerializedName("DeliveryEtaMinutes")
    val deliveryEtaMinutes: DeliveryEtaMinutes = DeliveryEtaMinutes(),
    @SerializedName("IsCollection")
    val isCollection: Boolean = false,
    @SerializedName("IsDelivery")
    val isDelivery: Boolean = false,
    @SerializedName("IsFreeDelivery")
    val isFreeDelivery: Boolean = false,
    @SerializedName("IsOpenNowForCollection")
    val isOpenNowForCollection: Boolean = false,
    @SerializedName("IsOpenNowForDelivery")
    val isOpenNowForDelivery: Boolean = false,
    @SerializedName("IsOpenNowForPreorder")
    val isOpenNowForPreorder: Boolean = false,
    @SerializedName("IsOpenNow")
    val isOpenNow: Boolean = false,
    @SerializedName("IsTemporarilyOffline")
    val isTemporarilyOffline: Boolean = false,
    @SerializedName("DeliveryMenuId")
    val deliveryMenuId: Int = 0,
    @SerializedName("DeliveryCost")
    val deliveryCost: Double = 0.0,
    @SerializedName("MinimumDeliveryValue")
    val minimumDeliveryValue: Double = 0.0,
    @SerializedName("SecondDateRanking")
    val secondDateRanking: Double = 0.0,
    @SerializedName("DefaultDisplayRank")
    val defaultDisplayRank: Int = 0,
    @SerializedName("SponsoredPosition")
    val sponsoredPosition: Int = 0,
    @SerializedName("SecondDateRank")
    val secondDateRank: Double = 0.0,
    @SerializedName("Score")
    val score: Double = 0.0,
    @SerializedName("IsTemporaryBoost")
    val isTemporaryBoost: Boolean = false,
    @SerializedName("IsSponsored")
    val isSponsored: Boolean = false,
    @SerializedName("IsPremier")
    val isPremier: Boolean = false,
    @SerializedName("ShowSmiley")
    val showSmiley: Boolean = false,
    @SerializedName("SmileyElite")
    val smileyElite: Boolean = false,
    @SerializedName("SendsOnItsWayNotifications")
    val sendsOnItsWayNotifications: Boolean = false,
    @SerializedName("BrandName")
    val brandName: String = "",
    @SerializedName("IsBrand")
    val isBrand: Boolean = false,
    @SerializedName("LastUpdated")
    val lastUpdated: String = "",
    @SerializedName("Deals")
    val deals: List<Any> = listOf(),
    @SerializedName("Offers")
    val offers: List<Any> = listOf(),
    @SerializedName("Logo")
    val logo: List<Logo> = listOf(),
    @SerializedName("Tags")
    val tags: List<String> = listOf(),
    @SerializedName("DeliveryChargeBands")
    val deliveryChargeBands: List<Any> = listOf(),
    @SerializedName("CuisineTypes")
    val cuisineTypes: List<CuisineType> = listOf(),
    @SerializedName("Cuisines")
    val cuisines: List<Cuisine> = listOf(),
    @SerializedName("ScoreMetaData")
    val scoreMetaData: List<ScoreMetaData> = listOf(),
    @SerializedName("Badges")
    val badges: List<Any> = listOf(),
    @SerializedName("OpeningTimes")
    val openingTimes: List<Any> = listOf(),
    @SerializedName("ServiceableAreas")
    val serviceableAreas: List<Any> = listOf(),
    @SerializedName("OpeningTimeUtc")
    val openingTimeUtc: Any = Any(),
    @SerializedName("DeliveryOpeningTimeUtc")
    val deliveryOpeningTimeUtc: Any = Any(),
    @SerializedName("DeliveryTime")
    val deliveryTime: Any = Any(),
    @SerializedName("DeliveryTimeMinutes")
    val deliveryTimeMinutes: Any = Any(),
    @SerializedName("CollectionMenuId")
    val collectionMenuId: Any = Any(),
    @SerializedName("DeliveryZipcode")
    val deliveryZipcode: Any = Any(),
    @SerializedName("HygieneRating")
    val hygieneRating: Any = Any(),
    @SerializedName("SmileyDate")
    val smileyDate: Any = Any(),
    @SerializedName("SmileyResult")
    val smileyResult: Any = Any(),
    @SerializedName("SmileyUrl")
    val smileyUrl: Any = Any()
)

data class Address(
    @SerializedName("City")
    val city: String = "",
    @SerializedName("FirstLine")
    val firstLine: String = "",
    @SerializedName("Postcode")
    val postcode: String = "",
    @SerializedName("Latitude")
    val latitude: Double = 0.0,
    @SerializedName("Longitude")
    val longitude: Double = 0.0
)

data class Rating(
    @SerializedName("Count")
    val count: Int = 0,
    @SerializedName("Average")
    val average: Double = 0.0,
    @SerializedName("StarRating")
    val starRating: Double = 0.0
)

data class DeliveryEtaMinutes(
    @SerializedName("Approximate")
    val approximate: Any = Any(),
    @SerializedName("RangeLower")
    val rangeLower: Int = 0,
    @SerializedName("RangeUpper")
    val rangeUpper: Int = 0
)

data class Logo(
    @SerializedName("StandardResolutionURL")
    val standardResolutionURL: String = ""
)

data class CuisineType(
    @SerializedName("Id")
    val id: Int = 0,
    @SerializedName("IsTopCuisine")
    val isTopCuisine: Boolean = false,
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("SeoName")
    val seoName: String = ""
)

data class Cuisine(
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("SeoName")
    val seoName: String = ""
)

data class ScoreMetaData(
    @SerializedName("Key")
    val key: String = "",
    @SerializedName("Value")
    val value: String = ""
)

data class RestaurantSet(
    @SerializedName("Id")
    val id: String = "",
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("Type")
    val type: String = "",
    @SerializedName("Restaurants")
    val restaurants: List<RestaurantX> = listOf()
)

data class RestaurantX(
    @SerializedName("Id")
    val id: Int = 0
)

data class CuisineSet(
    @SerializedName("Id")
    val id: String = "",
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("Type")
    val type: String = "",
    @SerializedName("Cuisines")
    val cuisines: List<CuisineX> = listOf()
)

data class CuisineX(
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("SeoName")
    val seoName: String = ""
)

data class View(
    @SerializedName("Target")
    val target: String = "",
    @SerializedName("Components")
    val components: List<Component> = listOf()
)

data class Component(
    @SerializedName("Type")
    val type: String = "",
    @SerializedName("Id")
    val id: String = "",
    @SerializedName("TrackingId")
    val trackingId: String = "",
    @SerializedName("TemplateName")
    val templateName: String = "",
    @SerializedName("ViewData")
    val viewData: ViewData = ViewData()
)

data class ViewData(
    @SerializedName("Title")
    val title: Any = Any(),
    @SerializedName("SubTitle")
    val subTitle: Any = Any(),
    @SerializedName("SeeAllSearchTarget")
    val seeAllSearchTarget: Any = Any(),
    @SerializedName("FocusedProperties")
    val focusedProperties: List<Any> = listOf()
)
