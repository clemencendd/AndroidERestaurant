package fr.isen.nadaud.androiderestaurant

import android.content.Context
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import fr.isen.nadaud.androiderestaurant.network.Dish
import java.io.File
import java.io.Serializable

class Basket(val items : MutableList<BasketItem>): Serializable {
    fun addItem(item: Dish, quantity: Int){
        //On récupère l'item équivalent au plat, si il existe
        val existingItem = items.firstOrNull { it.dish.name == item.name }
        //autre méthode : val existingItem = items.filter { it.dish.name == item.name }.firstOrNull()
        //S'il existe, on met à jour la quantité et sinon on crée l'item
        existingItem?.let{
            existingItem.quantity += quantity
        } ?: run {
            val basketItem = BasketItem(item, quantity)
            items.add(basketItem)
        }

    }

    fun save(context: Context){
        val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
        val json = GsonBuilder().create().toJson(this)
        jsonFile.writeText(json)
        //Log.d("basket", json)
    }

    companion object {
        fun getBasket(context: Context): Basket {
            val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
            if(jsonFile.exists()) {
                val json = jsonFile.readText()
                return GsonBuilder().create().fromJson(json, Basket::class.java)
            } else {
                return Basket(mutableListOf())
            }
        }

        const val BASKET_FILE = "basket.json"
    }
}

class BasketItem(val dish: Dish, var quantity: Int): Serializable{}