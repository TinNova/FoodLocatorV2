package tin.novakovic.foodlocator.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_restaurant.view.*
import tin.novakovic.foodlocator.R
import tin.novakovic.foodlocator.domain.Restaurant
import tin.novakovic.foodlocator.ui.MainAdapter.*

class MainAdapter :
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

        fun bind(restaurant: Restaurant, onItemClicked: (Restaurant) -> Unit) {

            itemView.restaurant_name.text = restaurant.name
            itemView.restaurant_foodType.text = restaurant.foodType
            itemView.restaurant_rating.text = restaurant.rating

            Picasso.get()
                .load(restaurant.logo)
                .into(itemView.restaurant_logo)

            itemView.setOnClickListener {
                onItemClicked(restaurant)
            }
        }

    }

}
