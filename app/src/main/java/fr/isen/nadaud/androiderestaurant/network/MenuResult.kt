package fr.isen.nadaud.androiderestaurant.network

import com.google.gson.annotations.SerializedName

class MenuResult(@SerializedName("data") val data : List<Category>){

}