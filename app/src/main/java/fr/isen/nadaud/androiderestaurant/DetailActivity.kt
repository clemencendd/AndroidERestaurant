package fr.isen.nadaud.androiderestaurant

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import fr.isen.nadaud.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.nadaud.androiderestaurant.network.Dish

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var currentDish: Dish? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentDish = intent.getSerializableExtra(CategoryActivity.ItemSelected) as? Dish
        setupContent()
            //peut etre nul à cause du cast
    }

    private fun setupContent(){
        binding.title.text = currentDish?.name

        //var string = ""
        //currentDish?.ingredients.forEach {
        //    string = "$(it.name), "
        // }
        binding.ingredients.text = currentDish?.ingredients?.map { it.ingredient }?.joinToString(", ")
        //map permet de convertir une liste

        currentDish?.let {

            binding.viewPager.adapter = PhotoAdapter(this, it.images)

        }

    }
}