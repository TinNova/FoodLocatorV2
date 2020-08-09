package tin.novakovic.foodlocator.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_restaurant.view.*
import tin.novakovic.foodlocator.R
import tin.novakovic.foodlocator.core.user_case_layer.Restaurant
import tin.novakovic.foodlocator.ui.MainAdapter.*

class MainAdapter :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    private var restaurants: List<Restaurant> = listOf()

    fun setData(data: List<Restaurant>) {
        restaurants = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = restaurants.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false))

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position])

    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(restaurant: Restaurant) {

            itemView.restaurant_name.text = restaurant.name
            itemView.restaurant_foodType.text = restaurant.foodType
            itemView.restaurant_rating.text = restaurant.rating

            Picasso.get()
                .load(restaurant.logo)
                .into(itemView.restaurant_logo)
        }
    }

}
