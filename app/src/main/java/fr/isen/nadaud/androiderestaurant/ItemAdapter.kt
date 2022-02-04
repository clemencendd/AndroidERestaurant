package fr.isen.nadaud.androiderestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.nadaud.androiderestaurant.databinding.CellMainBinding
import fr.isen.nadaud.androiderestaurant.network.Dish

class ItemAdapter(val items: List<Dish>, val itemClickListener: (Dish) -> Unit): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    //on crée un pont de discussion retour entre l'activité et notre adapter
    //itemClickListener : est un bloc de code qui ne retourne rien (Unit), il a besoin d'une string en paramètre pour s'exécuter

    class ItemViewHolder(binding: CellMainBinding): RecyclerView.ViewHolder(binding.root){
        //mapper le contenu (les attributs) de notre cellule, permet d'avoir une classe associée
        val title: TextView = binding.name
        //on crée le view model

        val price: TextView = binding.price
        val image: ImageView = binding.imageView

        //on doit récupérer le root de la cellule
        val layout: CardView = binding.root

    }

    override fun onCreateViewHolder(viewParent: ViewGroup, viewType: Int): ItemViewHolder {
        //on crée le view holder et attacher le layout à celui-ci
        val binding = CellMainBinding.inflate(LayoutInflater.from(viewParent.context), viewParent, false)
        //.context : sert à récupérer le contexte de l'activité
        //false : car on ne s'attache pas au parent (on est dans une cellule)
        return ItemViewHolder(binding)

    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        //appelé au moment de l'affichage de la cellule
        val item = items[position] //on recupere l'item
        viewHolder.title.text = item.name
        viewHolder.price.text = item.prices.first().price+ " €"
        //on récupère le premier prix dans la liste de prix
        //autre méthode : viewHolder.price.text = "${item.prices.first().price} €"
        Picasso.get() //lib pour récupérer les images
            .load(item.getThumbnailURL())
            .placeholder(R.drawable.capture)
            //on place une image par défaut en attendant le chargement
            .into(viewHolder.image)
        viewHolder.layout.setOnClickListener {
           itemClickListener.invoke(item)
            //itemClickListener variable qui représente un bloc de code
            //invoke : invoque le bloc de code dans CategoryActivity, fonction setupList

        }
    }

    override fun getItemCount(): Int {
        //on informe à la recycle view combien d'item nous avons dans la liste
        return items.count()
        //return items.size
    }
}