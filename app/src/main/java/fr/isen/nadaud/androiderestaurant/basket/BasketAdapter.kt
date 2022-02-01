package fr.isen.nadaud.androiderestaurant.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.nadaud.androiderestaurant.BasketItem
import fr.isen.nadaud.androiderestaurant.R
import fr.isen.nadaud.androiderestaurant.databinding.CellBasketBinding
import java.text.FieldPosition

class BasketAdapter(private val items: List<BasketItem>): RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    class BasketViewHolder(binding: CellBasketBinding): RecyclerView.ViewHolder(binding.root){
        val dishName: TextView = binding.name
        val price: TextView = binding.price
        val quantity: TextView = binding.quantity
        val delete: ImageButton = binding.deleteButton
        val imageView: ImageView = binding.imageView2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder{
        return BasketViewHolder(CellBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int){
        val basketItem = items[position]
        holder.dishName.text = basketItem.dish.name
        holder.quantity.text = basketItem.quantity.toString()
        holder.price.text = basketItem.dish.prices.first().price
        Picasso.get()
            .load(basketItem.dish.getThumbnailURL())
            .placeholder(R.drawable.capture)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}