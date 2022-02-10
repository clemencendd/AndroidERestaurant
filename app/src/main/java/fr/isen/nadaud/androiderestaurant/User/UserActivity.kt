package fr.isen.nadaud.androiderestaurant.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.nadaud.androiderestaurant.LoginFragment
import fr.isen.nadaud.androiderestaurant.R
import fr.isen.nadaud.androiderestaurant.RegisterFragment
import fr.isen.nadaud.androiderestaurant.databinding.ActivityUserBinding

interface UserActivityFragmentInteraction {
    //création d'une interface car on veut utiliser le fragment pour toute redirection et pas que par UserActivity
    fun showLogin()
    fun showRegister()
}

class UserActivity : AppCompatActivity(), UserActivityFragmentInteraction {
    lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
        // add car il n'y a pas de fragment existant dans le container
        //commmit() : permet de finir la transaction
    }

    override fun showLogin(){
        //afficher le fragment Login
        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, loginFragment).commit()
    }

    override fun  showRegister() {
        //afficher le fragment Register

    }
}