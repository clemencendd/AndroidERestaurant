package fr.isen.nadaud.androiderestaurant //

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.nadaud.androiderestaurant.databinding.ActivityHomeBinding

enum class ItemType {
    STARTERS, DISHES, DESSERTS;
}

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root) //va permettre de créer une instance pour détecter toutes les interations qu'on va avoir avec le layout

        listenClick()

        Log.d("life cycle", "HomeActivity onCreate") //onCreate est une méthode

    }

    private fun listenClick() {
        binding.ButtonStarter.setOnClickListener { //Appeler le bloc de code lorsque l'on clique sur le bouton
            //Toast.makeText(this, R.string.ButtonStarter, Toast.LENGTH_LONG).show()
            showCategory(LunchType.STARTERS)
        }

        binding.ButtonDish.setOnClickListener { //Appeler le bloc de code lorsque l'on clique sur le bouton
            //Toast.makeText(this, R.string.ButtonDish, Toast.LENGTH_LONG).show()
            showCategory(LunchType.DISHES)
        }

        binding.ButtonDessert.setOnClickListener { //Appeler le bloc de code lorsque l'on clique sur le bouton
            //Toast.makeText(this, R.string.ButtonDessert, Toast.LENGTH_LONG).show()
            showCategory(LunchType.DESSERTS)
        }

    }

    private fun showCategory(item : LunchType) {
        val intent = Intent (this@HomeActivity, CategoryActivity::class.java)
        intent.putExtra(HomeActivity.CategoryType, item) //va permettre de savoir dans quel catégorie nous sommes
        startActivity(intent) //méthode qui démarre l'intent
    }

    companion object {
        const val CategoryType = "CategoryType"
    }


    override fun onDestroy() {
        Log.d("life cycle", "HomeActivity on Destroy")
        super.onDestroy()

    }

    override fun onStart() {
        Log.d("life cycle", "HomeActivity on Destroy")
        super.onStart()

    }

    override fun onPause() {
        Log.d("life cycle", "HomeActivity on Pause")
        super.onPause()

    }

    override fun onResume() {
        Log.d("life cycle", "HomeActivity on Resume")
        super.onResume()

    }

    override fun onStop() {
        Log.d("life cycle", "HomeActivity on Stop")
        super.onStop()

    }

}
