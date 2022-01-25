package fr.isen.nadaud.androiderestaurant

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.nadaud.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.nadaud.androiderestaurant.network.NetworkConstants
import org.json.JSONObject

enum class LunchType { //enum = type de variable qui ne prend que les variables définies
    STARTERS, DISHES, DESSERTS;

    companion object { //permet de stocker les états et les méthodes communs à toutes les instances d'une classe
        fun getResString(type: LunchType):Int { //on crée une fonction qui va retourner les ID des entrées, plats et desserts
            return when(type) {
                STARTERS -> R.string.Starters //name de la string du bouton des entrées
                DISHES -> R.string.Dishes //name de la string du bouton des plats
                DESSERTS -> R.string.Desserts //name de la string du bouton des desserts
            }
        }
    }
}


class CategoryActivity : AppCompatActivity() {
    lateinit var  binding: ActivityCategoryBinding
    lateinit var currentCategory: LunchType

    val fakeItems = listOf("item1","item2","item3","item4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val item: LunchType = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTERS
        //getSerializableExtra : on ne sait pas à quelle classe on appartient suite au put donc on sérialise l'objet
        //as? LunchType : on lui précise donc la classe, on le caste en LunchType
        //ATTENTION : Le point d'interrogation permet de ne pas cracher, retourner une variable de type nul si il n'y a pas de variable castée
        //ATTENTION : ne jamais utiliser de doubles points d'exclamation (on ne sait pas ce que cela renvoie, donc crash de l'application)
        //?: LunchType.STARTERS : permet de donner une valeur par défaut, ici sur STARTERS (pour ne pas gérer une valeur nulle)
        currentCategory = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTERS
        setupTitle()
        setupList()
        makeRequest()

        Log.d("life cycle", "CategoryActivity onCreate") //onCreate est une méthode

        //binding.textView.setText("extra "+item)
    }

    private fun setupTitle() {

        binding.title.text = getString(LunchType.getResString(currentCategory)) //getString : convertir une ressource ID (le name de la string) en string
    }

    private fun setupList() {

        binding.itemRecyclerView.layoutManager = LinearLayoutManager(this) //permet d'afficher la liste
        val adapter = ItemAdapter(fakeItems) { selectedItem -> //permet de forcer le nommage
            //closure
            //Log.d("debug", it)

            showDetail(selectedItem)
        }
        //deux arguments mis en paramètres
        binding.itemRecyclerView.adapter = adapter

    }

    private fun makeRequest() {

        Log.d("enter", "enter")
        val queue = Volley.newRequestQueue(this)
        val url = NetworkConstants.BASE_URL+NetworkConstants.MENU
        val parameters = JSONObject() //dictionnaire de type JSON donc on put des elts
        parameters.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)
        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            parameters,
            {
                //retour de la requête si ça s'est bien passé
                Log.d("volley","${it.toString(2)}")
            },
            {
                //retour de la requête si erreur (ex: pas de connexion, 404...)
                Log.d("volley error","$it")
            })
        queue.add(request)
    }

    private fun showDetail(item: String){
        val intent = Intent (this@CategoryActivity, DetailActivity::class.java)
        intent.putExtra(CategoryActivity.ItemSelected, item) //va permettre de savoir dans quel catégorie nous sommes
        startActivity(intent) //méthode qui démarre l'intent

    }

    companion object {
        const val ItemSelected = "ItemSelected"
    }

    override fun onDestroy() {
        Log.d("life cycle", "CategoryActivity on Destroy")
        super.onDestroy()

    }

    override fun onStart() {
        Log.d("life cycle", "CategoryActivity on Destroy")
        super.onStart()

    }

    override fun onPause() {
        Log.d("life cycle", "CategoryActivity on Pause")
        super.onPause()

    }

    override fun onResume() {
        Log.d("life cycle", "CategoryActivity on Resume")
        super.onResume()

    }

    override fun onStop() {
        Log.d("life cycle", "CategoryActivity on Stop")
        super.onStop()

    }

}





