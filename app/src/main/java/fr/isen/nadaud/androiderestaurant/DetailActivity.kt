package fr.isen.nadaud.androiderestaurant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import fr.isen.nadaud.androiderestaurant.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.text = intent.getStringExtra(CategoryActivity.ItemSelected)
    }
}