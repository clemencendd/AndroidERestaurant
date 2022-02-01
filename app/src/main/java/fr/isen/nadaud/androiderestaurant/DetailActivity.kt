package fr.isen.nadaud.androiderestaurant

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import fr.isen.nadaud.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.nadaud.androiderestaurant.network.Dish
import android.content.Context
import android.media.Image
import android.widget.Button
import android.widget.ImageButton

import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlin.math.max


class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var currentDish: Dish? = null
    //private var itemCount=1
    private var cpt = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentDish = intent.getSerializableExtra(CategoryActivity.ItemSelected) as? Dish
        //peut etre nul à cause du cast
        setupContent()

        refreshShopButton()


        binding.imageButton2.setOnClickListener {
            cpt++
            binding.quantity.setText("${cpt.toInt()}")
            refreshShopButton()

        }
        binding.imageButton1.setOnClickListener {
            cpt = max(1f, cpt-1)
            binding.quantity.setText("${cpt.toInt()}")
            refreshShopButton()

        }
        binding.buttonShop.setOnClickListener {
            currentDish?.let { dish ->
                val basket = Basket.getBasket(this)
                basket.addItem(dish, cpt.toInt())
                basket.save(this)
                Snackbar.make(binding.root, R.string.itemAdded, Snackbar.LENGTH_LONG).show()
            }

        }
    }



    private fun refreshShopButton() {
        currentDish?.let { dish ->
            val price: Float = dish.prices.first().price.toFloat()
            val total = price * cpt
            binding.buttonShop.text = "${getString(R.string.total)} : $total euros"

        }
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

    /*

    private fun refreshShopButton() {
        currentDish?.let { dish ->
            val price: Float = dish.prices.first().price.toFloat()
            val total = price * itemCount
            binding.buttonShop.text = "${getString(R.string.total)} $total euros"
            binding.quantity.text = itemCount.toInt().toString()


        }
    }


    private fun observeClick() {
        binding.imageButton1.setOnClickListener {
            itemCount = max(1f, itemCount-1)
            //1f : on précise que c'est un float
            refreshShopButton()
        }

        binding.imageButton2.setOnClickListener {
            itemCount++
            refreshShopButton()

        }

        binding.buttonShop.setOnClickListener {
            currentDish?.let { dish ->
                val basket = Basket.getBasket()
                basket.addItem(dish, itemCount.toInt())
            }

        }
    }

     */
}

