package fr.isen.nadaud.androiderestaurant

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import fr.isen.nadaud.androiderestaurant.databinding.FragmentPhotoBinding
import java.net.URL

class PhotoFragment : Fragment() {

    private lateinit var binding: FragmentPhotoBinding

    companion object {

        const val URL = "url"

        fun newInstance(url: String) : PhotoFragment{
            return PhotoFragment().apply {
                //this car c'est notre classe une fois qu'elle a été instanciée
                arguments = Bundle().apply {
                    putString(URL, url)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //le seul moment où on appele le super c'est sur les méthodes d'arrêt : onStop, onDestroy, onPause ...
        val url = arguments?.getString(URL)
        if (url?.isNotEmpty() == true){ //boolean optionnel sans true, if accepte pas
            Picasso.get().load(url).placeholder(R.drawable.capture).into(binding.photoFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

}