package fr.isen.nadaud.androiderestaurant

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import fr.isen.nadaud.androiderestaurant.databinding.CellMainBinding

class ItemAdapter(val items: List<String>, val itemClickListener: (String) -> Unit): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    //on crée un pont de discussion retour entre l'activité et notre adapter
    //itemClickListener : est un bloc de code qui ne retourne rien (Unit), il a besoin d'une string en paramètre pour s'exécuter

    class ItemViewHolder(binding: CellMainBinding): RecyclerView.ViewHolder(binding.root){
        //mapper le contenu (les attributs) de notre cellule, permet d'avoir une classe associée
        val title: TextView = binding.textView
        //on crée le view model

        //on doit récupérer le root de la cellule
        val layout: ConstraintLayout = binding.root

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
        viewHolder.title.text = item

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