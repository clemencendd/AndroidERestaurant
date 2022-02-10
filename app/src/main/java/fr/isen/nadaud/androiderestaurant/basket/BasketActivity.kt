package fr.isen.nadaud.androiderestaurant.basket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.nadaud.androiderestaurant.Basket
import fr.isen.nadaud.androiderestaurant.User.UserActivity
import fr.isen.nadaud.androiderestaurant.databinding.ActivityBasketBinding

class BasketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadList()

    binding.orderButton.setOnClickListener {
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
    }
}

    private fun loadList() {
        val basket = Basket.getBasket(this)
        val items = basket.items
        //code de la recycler view
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = BasketAdapter(items) {
            basket.removeItem(it)
            basket.save(this)
            loadList()
        }
    }
}