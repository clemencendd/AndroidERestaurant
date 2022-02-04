package fr.isen.nadaud.androiderestaurant

import android.content.Context
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import fr.isen.nadaud.androiderestaurant.network.Dish
import java.io.File
import java.io.Serializable

class Basket(val items : MutableList<BasketItem>): Serializable {
    var cpt: Int = 0

    get() {
        //compter tous les items
        // exemple : 3 salades et 5 tartares doivent retourner 8
        /*
        var count = 0
        items.forEach{
            count += it.quantity
        }
        return count
        */

        //autre méthode :
        val count = items.map {
            it.quantity
        }.reduceOrNull { acc, i -> acc + i } ?: 0  //?: = valeur par défaut
        return count ?: 0
    }


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

    fun removeItem(basketItem: BasketItem) {
        items.remove(basketItem)
    }

    fun save(context: Context){
        val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
        val json = GsonBuilder().create().toJson(this)
        jsonFile.writeText(json)
        Log.d("basket", json)
        updateCounter(context)
    }

    private fun updateCounter(context : Context) {
        val sharedPreferences = context.getSharedPreferences(USER_PREFERENCES_NAME,  Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ITEMS_COUNT, cpt)
        editor.apply()
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
        const val ITEMS_COUNT = "ITEMS_COUNT"
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
    }
}

class BasketItem(val dish: Dish, var quantity: Int): Serializable{}