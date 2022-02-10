package fr.isen.nadaud.androiderestaurant

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import fr.isen.nadaud.androiderestaurant.User.UserActivity
import fr.isen.nadaud.androiderestaurant.User.UserActivityFragmentInteraction
import fr.isen.nadaud.androiderestaurant.databinding.FragmentRegisterBinding


class RegisterFragment () : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    var interactor: UserActivityFragmentInteraction? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        interactor = context as? UserActivityFragmentInteraction
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            interactor?.showLogin()
        }
        binding.createButton.setOnClickListener {
            //on cherche à récupérer les elts du formulaire et les passer à l'activité

        }
    }
}