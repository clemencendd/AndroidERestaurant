package fr.isen.nadaud.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Ingredient(@SerializedName("name_fr") val ingredient: String): Serializable {
    //paramètre passé directement au constructeur
}

