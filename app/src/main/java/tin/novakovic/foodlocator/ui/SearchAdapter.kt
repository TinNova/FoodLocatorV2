package tin.novakovic.foodlocator.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tin.novakovic.foodlocator.R
import tin.novakovic.foodlocator.databinding.ItemRestaurantBinding
import tin.novakovic.foodlocator.domain.Restaurant
import tin.novakovic.foodlocator.ui.SearchAdapter.*

class SearchAdapter :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    private var restaurants: List<Restaurant> = listOf()
    private lateinit var onItemClicked: (Restaurant) -> Unit

    fun setData(data: List<Restaurant>) {
        restaurants = data
        notifyDataSetChanged()
    }

    fun clickListener(onItemClicked: (Restaurant) -> Unit) {
        this.onItemClicked = onItemClicked
    }

    override fun getItemCount(): Int = restaurants.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        )

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position], onItemClicked)
    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRestaurantBinding.bind(itemView)

        fun bind(restaurant: Restaurant, onItemClicked: (Restaurant) -> Unit) {

            binding.restaurantName.text = restaurant.name
            binding.restaurantFoodType.text = restaurant.foodType
            binding.restaurantRating.text = restaurant.rating

            Picasso.get()
                .load(restaurant.logo)
                .into(binding.restaurantLogo)

            itemView.setOnClickListener {
                onItemClicked(restaurant)
            }
        }

    }

}
