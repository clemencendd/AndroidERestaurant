package fr.isen.nadaud.androiderestaurant.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.nadaud.androiderestaurant.LoginFragment
import fr.isen.nadaud.androiderestaurant.R
import fr.isen.nadaud.androiderestaurant.RegisterFragment
import fr.isen.nadaud.androiderestaurant.databinding.ActivityUserBinding
import fr.isen.nadaud.androiderestaurant.network.NetworkConstants
import org.json.JSONObject

interface UserActivityFragmentInteraction {
    //création d'une interface car on veut utiliser le fragment pour toute redirection et pas que par UserActivity
    fun showLogin()
    fun showRegister()
    fun makeRequest(email: String?, password: String?, firstname: String?, lastname: String?, isFromLogin: Boolean)
}

class UserActivity : AppCompatActivity(), UserActivityFragmentInteraction {
    lateinit var binding: ActivityUserBinding
    val loginFragment = LoginFragment()
    val registerFragment = RegisterFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, registerFragment).commit()
        // add car il n'y a pas de fragment existant dans le container
        //commmit() : permet de finir la transaction
    }

    override fun showLogin(){
        //afficher le fragment Login
        //val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, loginFragment).commit()
    }

    override fun  showRegister() {
        //afficher le fragment Register
        //val registerFragment = RegisterFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, registerFragment).commit()
    }

    override fun makeRequest(
        email: String?,
        password: String?,
        firstname: String?,
        lastname: String?,
        isFromLogin: Boolean
    ) {
        if(verifyInformation(email, password, firstname, lastname, isFromLogin)){
            //launch request
            launchRequest(email, password, firstname, lastname, isFromLogin)
        } else {
            //affiche un message champs invalide
            Toast.makeText(this, getString(R.string.invalidForms), Toast.LENGTH_LONG).show()
        }
    }

    private fun launchRequest(
        email: String?,
        password: String?,
        firstname: String?,
        lastname: String?,
        isFromLogin: Boolean
    ){
        val queue = Volley.newRequestQueue(this)
        var requestPath = NetworkConstants.BASE_URL
        if(isFromLogin) {
            requestPath += NetworkConstants.LOGIN
        } else {
            requestPath += NetworkConstants.REGISTER
        }

        val parameters = JSONObject()
        parameters.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)
        parameters.put(NetworkConstants.KEY_EMAIL, email)
        parameters.put(NetworkConstants.KEY_PASSWORD, password)

        if(!isFromLogin) {
            parameters.put(NetworkConstants.KEY_FIRSTNAME, firstname)
            parameters.put(NetworkConstants.KEY_LASTNAME, lastname)
        }

        val request = JsonObjectRequest(
            Request.Method.POST,
            requestPath,
            parameters,
            {
                //Success
                Log.d("request", it.toString(2))
            },
            {
                //Failure
                Log.d("request", it.localizedMessage)
            }
        )
        queue.add(request)
    }


    private fun verifyInformation(
        email: String?,
        password: String?,
        firstname: String?,
        lastname: String?,
        isFromLogin: Boolean
    ): Boolean {
        var verified = (email?.isNotEmpty() == true && password?.count() ?: 0 >= 6)

        if(!isFromLogin) {
            verified = verified && (firstname?.isNotEmpty() == true && lastname?.isNotEmpty() == true)
        }
        return verified
    }
}